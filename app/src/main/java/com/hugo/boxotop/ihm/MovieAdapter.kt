package com.hugo.boxotop.ihm

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hugo.boxotop.R
import com.hugo.boxotop.inflate

/**
 * Created by hpatural on 26/03/2018.
 */
class MovieAdapter (val context: Context) : RecyclerView.Adapter<MovieAdapter.MovieHolder>(), View.OnClickListener {

    lateinit var mRecyclerView: RecyclerView


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView!!
    }

    override fun onBindViewHolder(holder: MovieHolder?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieHolder {

        val inflatedView = parent!!.inflate(R.layout.item_movie, false)
        return MovieHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onClick(view: View?) {
        val itemPosition = mRecyclerView.getChildLayoutPosition(view)
    }

    class MovieHolder(row: View?): RecyclerView.ViewHolder(row) {

        init {


        }
    }
}