package com.hugo.boxotop.ihm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hugo.boxotop.network.APIUtils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by hpatural on 24/03/2018.
 */
class MainActivity : AppCompatActivity(), Callback<ResponseBody> {
    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
        println("failure") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
        println("ok good") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        APIUtils.getOpenMovieAPI().getFilms("test", "feaf59e0").enqueue(this)
    }

}