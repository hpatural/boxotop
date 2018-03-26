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

        //Set the toolbar with search bar
        setSupportActionBar(toolbar)

        //Init the current fragment and display it in the container
        currentFragment = SearchFragment.newInstance()
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, currentFragment, SearchFragment.TAG)
                .commitAllowingStateLoss()

    }


    //Init the menu with search bar
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
        //On query submit we launch search to API
        if (currentFragment is SearchFragment && p0 != null) {
            (currentFragment as SearchFragment).searchSubmit(p0)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        //Here we could do auto-completion
        return true
    }

    override fun onChangeFragment(fragment: Fragment) {
        print("change fragment todo")
        currentFragment = fragment
    }



}