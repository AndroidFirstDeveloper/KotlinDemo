package com.example.czl

import android.app.Application

class MyApplication : Application {

    constructor()

    override fun onCreate() {
        super.onCreate()
        println("application is launch")
        System.setProperty("kotlinx.coroutines.debug", "on")
    }
}