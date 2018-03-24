package com.hugo.boxotop.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by hpatural on 24/03/2018.
 */
interface OpenMovieAPI {
    @GET(".")
    fun getFilms(
            @Query("t") title: String,
            @Query("apikey") apiKey: String) : Call<ResponseBody>

    @GET(".")
    fun getFilmById(
            @Query("i") id: String) : Call<ResponseBody>
}