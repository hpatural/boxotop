package com.hugo.boxotop.network

/**
 * Created by hpatural on 24/03/2018.
 */
class APIUtils {

    companion object {
        val BASE_URL = "http://www.omdbapi.com"

        fun getOpenMovieAPI(): OpenMovieAPI {
            return RetrofitClient.getClient(BASE_URL).create(OpenMovieAPI::class.java)
        }
    }

}