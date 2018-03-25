package com.hugo.boxotop.network

import com.hugo.boxotop.BoxotopApplication
import com.hugo.boxotop.R
import okhttp3.Interceptor
import okhttp3.Response



/**
 * Created by hpatural on 24/03/2018.
 */
class ErrorInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("apikey", BoxotopApplication.context.getString(R.string.apikey)).build()
        request = request.newBuilder().url(url).build();

        val response = chain.proceed(request)

        if (response.code() != 200) {

            //YourBoardApplication.context.toast(response.body().string())

        }

        return response;
    }
}