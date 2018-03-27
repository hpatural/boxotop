package com.hugo.boxotop.ihm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hugo.boxotop.R
import com.hugo.boxotop.model.Movie
import com.hugo.boxotop.utils.ImageUtils
import kotlinx.android.synthetic.main.fragment_movie_details.*

/**
 * Created by hpatural on 26/03/2018.
 */
class MovieDetailsFragment: AbstractFragment() {


    lateinit var movie: Movie

    companion object {
        val MOVIE_ARG = "MOVIE_ARG"
        val TAG = MovieDetailsFragment.javaClass.canonicalName
        fun newInstance(movie: Movie): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val args = Bundle()
            args.putSerializable(MOVIE_ARG, movie)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (initArgs()) {
            movieTitle.text = movie.Title
            ImageUtils.loadImage(activity, moviePoster, movie.Poster!!)
        }


    }

    private fun initArgs(): Boolean {
        if (arguments != null) {
            movie = arguments.getSerializable(MOVIE_ARG) as Movie
            return true
        }
        return false
    }

    override fun showBackButton() {
        mListener.onShowBackButton(true)
    }
}