package com.hugo.boxotop.interfaces

import android.app.Fragment

/**
 * Created by hpatural on 26/03/2018.
 */
interface IFragmentListener {
    fun onChangeFragment(fragment: Fragment)

    fun onShowToolbar(showBackButton: Boolean, showSearchBar: Boolean)
}