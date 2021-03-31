package com.example.czl2

import android.app.Application
import org.greenrobot.eventbus.EventBus

class MyApplication : Application {

    constructor()

    override fun onCreate() {
        super.onCreate()
        println("application is launch")
        System.setProperty("kotlinx.coroutines.debug", "on")
//        EventBus.builder().throwSubscriberException(BuildConfig.DEBUG).addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }
}