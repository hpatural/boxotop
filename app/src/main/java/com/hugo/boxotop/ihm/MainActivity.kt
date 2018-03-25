package com.hugo.boxotop.ihm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.hugo.boxotop.network.APIUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by hpatural on 24/03/2018.
 */
class MainActivity : AppCompatActivity() {
    private var disposable: Disposable? = null
    private val openMovieAPI = APIUtils.getOpenMovieAPI()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beginSearch("Batman")

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


}