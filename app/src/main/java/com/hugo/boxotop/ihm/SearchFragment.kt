package com.hugo.boxotop.ihm

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hugo.boxotop.R
import com.hugo.boxotop.interfaces.IFragmentListener

/**
 * Created by hpatural on 26/03/2018.
 */
class SearchFragment: Fragment() {

    lateinit var mListener : IFragmentListener

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
    }

    override fun onAttach(activity: Context?) {
        super.onAttach(activity)

        if (activity is IFragmentListener) {
            mListener = activity
        } else {
            throw RuntimeException(activity!!.toString() + "must implement IFragmentInteractionListener")
        }
    }
}