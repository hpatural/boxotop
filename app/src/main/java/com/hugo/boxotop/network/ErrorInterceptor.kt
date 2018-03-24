package com.hugo.boxotop.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by hpatural on 24/03/2018.
 */
class ApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response = chain.proceed(request)

        if (response.code() != 200) {

            //YourBoardApplication.context.toast(response.body().string())

        }

        return response;
    }
}