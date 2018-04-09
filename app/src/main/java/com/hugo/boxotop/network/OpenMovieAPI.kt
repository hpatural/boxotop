package com.hugo.boxotop.network

import com.hugo.boxotop.model.Model
import com.hugo.boxotop.model.Movie
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by hpatural on 24/03/2018.
 */
interface OpenMovieAPI {

    @GET(".")
    fun getMovieById(
            @Query("i") id: String) :Observable<Movie>

    @GET(".")
    fun searchMovies(
            @Query("s") id: String,
            @Query("page") page: Int?) : Observable<Model.SearchResult>
}