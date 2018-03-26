package com.hugo.boxotop.ihm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.Toast
import com.hugo.boxotop.R
import com.hugo.boxotop.network.APIUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.widget.SearchView


/**
 * Created by hpatural on 24/03/2018.
 */
class MainActivity : AppCompatActivity() {
    private var disposable: Disposable? = null
    private val openMovieAPI = APIUtils.getOpenMovieAPI()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        // Note that the Toolbar defined in the layout has the id "my_toolbar"
        setSupportActionBar(findViewById(R.id.toolbar))
        handleIntent(intent)

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SearchFragment.newInstance(), SearchFragment.TAG)
                .commitAllowingStateLoss()

    }

    private fun beginSearch(searchString: String) {
        disposable = openMovieAPI.searchMovies(searchString, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            print(result)
                        },
                        { error ->
                            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                        }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))

        return true
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            beginSearch(query)

            //use the query to search your data somehow
        }
    }

}