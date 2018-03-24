package com.hugo.boxotop.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by hpatural on 24/03/2018.
 */
interface OpenMovieAPI {
    @GET("")
    fun getFilms(
            @Path("t") title: String) : Call<ResponseBody>

    @GET("")
    fun getFilmById(
            @Path("i") id: String) : Call<ResponseBody>
}