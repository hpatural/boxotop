package com.hugo.boxotop

import android.app.Application
import android.content.Context

/**
 * Created by hpatural on 25/03/2018.
 */
class BoxotopApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}


val Context.myApp: BoxotopApplication
    get() = applicationContext as BoxotopApplication