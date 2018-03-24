package com.hugo.boxotop.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by hpatural on 24/03/2018.
 */
class RetrofitClient {

    companion object {
        fun getClient(baseUrl: String): Retrofit {

            val client = OkHttpClient().newBuilder()
                    //.addInterceptor(RetrofitInterceptor())
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build()


            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create().asLenient())
                    .build()
        }
    }





}