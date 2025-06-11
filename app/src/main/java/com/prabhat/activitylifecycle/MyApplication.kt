package com.prabhat.activitylifecycle

import android.app.Application
import android.content.Context

class MyApplication: Application() {

    override fun onCreate() {

        super.onCreate()
        //when application is destroyed this application wide context is also destroyed
        val myContext:Context = this
    }
}