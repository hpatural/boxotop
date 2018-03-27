package com.hugo.boxotop.ihm.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hugo.boxotop.R
import com.hugo.boxotop.inflate
import com.hugo.boxotop.model.Movie
import com.hugo.boxotop.utils.ImageUtils

/**
 * Created by hpatural on 26/03/2018.
 */
class MovieAdapter (val context: Context, var movies: ArrayList<Movie>, val clickListener: View.OnClickListener) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    lateinit var mRecyclerView: RecyclerView


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView!!
    }

    override fun onBindViewHolder(holder: MovieHolder?, position: Int) {
        val movie = movies.get(position)
        if (holder != null) {
            holder.title.text = movie.Title
            if (movie.Poster != null) {
                ImageUtils.loadImage(context, holder.poster, movie.Poster!!)
            }
            holder.itemView.setOnClickListener(clickListener)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieHolder {

        val inflatedView = parent!!.inflate(R.layout.item_movie, false)
        return MovieHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    class MovieHolder(row: View?): RecyclerView.ViewHolder(row) {
        val title: TextView
        val poster: ImageView
        init {
            this.title = row?.findViewById(R.id.movieTitle)!!
            this.poster = row?.findViewById(R.id.moviePoster)!!
        }
    }
}