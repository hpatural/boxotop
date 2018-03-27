package com.hugo.boxotop.ihm.fragments

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    lateinit var movieAdapter: MovieAdapter

    companion object {
        val TAG = SearchFragment.javaClass.canonicalName
        fun newInstance(): SearchFragment {
            val fragment = SearchFragment()

            return fragment
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
        var linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        searchResultRV.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(searchResultRV.getContext(),
                linearLayoutManager.getOrientation())
        searchResultRV.addItemDecoration(dividerItemDecoration)
    }

    fun searchSubmit(query: String) {
        beginSearch(query)
    }

    private fun beginSearch(searchString: String) {
        disposable = openMovieAPI.searchMovies(searchString, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result.Search != null) {
                                movies = result.Search!!

                                this.movieAdapter.movies = movies
                                this.movieAdapter.notifyDataSetChanged()
                            }

                        },
                        { error ->
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