package com.hugo.boxotop.network

import com.hugo.boxotop.BoxotopApplication
import com.hugo.boxotop.R

/**
 * Created by hpatural on 24/03/2018.
 */
class APIUtils {

    companion object {
        val BASE_URL = BoxotopApplication.context.getString(R.string.base_url)

        fun getOpenMovieAPI(): OpenMovieAPI {
            return RetrofitClient.getClient(BASE_URL).create(OpenMovieAPI::class.java)
        }
    }

}