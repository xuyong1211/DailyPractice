package com.xy.dailypractice

import android.app.Application
import android.content.Context

class MyApplication() :Application() {
    companion object{
        lateinit var Instant :Application
    }
    override fun onCreate() {
        super.onCreate()
        Instant =  this
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

    }
}