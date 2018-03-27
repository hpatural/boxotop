package com.hugo.boxotop.ihm.fragments

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.View
import com.hugo.boxotop.interfaces.IFragmentListener

/**
 * Created by hpatural on 26/03/2018.
 */
abstract class AbstractFragment : Fragment() {

    lateinit var mListener : IFragmentListener

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBackButton()
    }

    //Attach the fragment listener interface
    override fun onAttach(activity: Context?) {
        super.onAttach(activity)

        if (activity is IFragmentListener) {
            mListener = activity
        } else {
            throw RuntimeException(activity!!.toString() + "must implement IFragmentInteractionListener")
        }
    }

    abstract fun showBackButton()
}