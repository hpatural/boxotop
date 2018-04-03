package com.hugo.boxotop.ihm.fragments

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.hugo.boxotop.R
import com.hugo.boxotop.ihm.adapters.MovieAdapter
import com.hugo.boxotop.model.Movie
import com.hugo.boxotop.network.APIUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by hpatural on 26/03/2018.
 */
class SearchFragment: AbstractFragment() {


    var disposable: Disposable? = null
    val openMovieAPI = APIUtils.getOpenMovieAPI()
    var movies = ArrayList<Movie>()
    var isLoading = false
    var moviesPage = 1
    var moviesTotalResult = -1
    var currentSearchString = ""
    var moviesLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


    lateinit var movieAdapter: MovieAdapter

    companion object {
        val TAG = SearchFragment.javaClass.canonicalName
        fun newInstance(): SearchFragment {
            val fragment = SearchFragment()

            return fragment
        }
    }

    //Custom recyclerview scroll listener for paging on scroll
    internal var scrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0)
                //check for scroll down
                {
                    val visibleItemCount = moviesLayoutManager.getChildCount()
                    val totalItemCount = moviesLayoutManager.getItemCount()
                    val pastVisiblesItems = moviesLayoutManager.findFirstVisibleItemPosition()

                    if (!isLoading)
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            if (moviesTotalResult != -1 && moviesTotalResult > movies.size)
                            beginSearch(currentSearchString, false)
                        }
                }
            }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.movieAdapter = MovieAdapter(activity, movies, View.OnClickListener {
            val itemPosition = searchResultRV.getChildLayoutPosition(it)
            mListener.onChangeFragment(MovieDetailsFragment.newInstance(movies.get(itemPosition)))
        })
        searchResultRV.adapter = movieAdapter
        searchResultRV.layoutManager = moviesLayoutManager
        val dividerItemDecoration = DividerItemDecoration(searchResultRV.getContext(),
                moviesLayoutManager.getOrientation())
        searchResultRV.addItemDecoration(dividerItemDecoration)

        searchResultRV.addOnScrollListener(scrollListener)
    }

    fun searchSubmit(query: String) {
        beginSearch(query, true)
    }

    private fun beginSearch(searchString: String, newSearch: Boolean) {
        isLoading = true

        if (newSearch) {
            moviesPage = 1
            currentSearchString = searchString
        }
        disposable = openMovieAPI.searchMovies(searchString, moviesPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result.Search != null) {
                                moviesTotalResult = Integer.parseInt(result.totalResults)
                                isLoading = false
                                movies.addAll(result.Search!!)
                                moviesPage++
                                this.movieAdapter.movies = movies
                                this.movieAdapter.notifyDataSetChanged()
                            }

                        },
                        { error ->
                            isLoading = false
                            Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
                        }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun showToolbar() {
        mListener.onShowToolbar(false, true)
    }


}