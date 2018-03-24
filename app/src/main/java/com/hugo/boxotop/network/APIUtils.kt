package com.hugo.boxotop.network

/**
 * Created by hpatural on 24/03/2018.
 */
class APIUtils {

    companion object {
        val BASE_URL = "http://optc-api.ptc-ophone.com/api/v1/"

        fun getZirconAPI(): OpenMovieAPI {
            return RetrofitClient.getClient(BASE_URL).create(OpenMovieAPI::class.java)
        }
    }

}