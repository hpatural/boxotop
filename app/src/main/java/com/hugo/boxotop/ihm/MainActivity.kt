package com.hugo.boxotop.ihm

import android.app.Fragment
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.SearchView
import com.hugo.boxotop.R
import com.hugo.boxotop.interfaces.IFragmentListener
import kotlinx.android.synthetic.main.main_activity.*


/**
 * Created by hpatural on 24/03/2018.
 */
class MainActivity : AppCompatActivity(), IFragmentListener, SearchView.OnQueryTextListener  {

    lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        // Note that the Toolbar defined in the layout has the id "my_toolbar"
        setSupportActionBar(toolbar)


        currentFragment = SearchFragment.newInstance()


        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, currentFragment, SearchFragment.TAG)
                .commitAllowingStateLoss()

    }



    override fun onPause() {
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(this)
        searchView.setSubmitButtonEnabled(true)



        return true
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if (currentFragment is SearchFragment && p0 != null) {
            (currentFragment as SearchFragment).searchSubmit(p0)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }

    override fun onChangeFragment(fragment: Fragment) {
        print("change fragment todo")
        currentFragment = fragment
    }



}