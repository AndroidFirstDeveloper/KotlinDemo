package com.example.czl2.component

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class Service1 : Service() {

    //通过startService启动时，总结
    //1 重复调用startService时onCreate方法只执行一次，onStartCommand、onStart执行多次
    //2 调用stopService时service停止
    //3 调用startService的组件销毁时service并不销毁

    //通过bindService启动时，总结
    //1 重复调用bindService时，onCreate、onBind只执行一次，onStartCommand、onStart不会执行
    //2 调用unbindService时，onUnbind方法执行，service停止;多次调用的话会抛异常
    //3 调用bindService的组件销毁时如果没有其它client继续跟service连接的话，service自动销毁
    //4 通过bindService启动的service无法使用stopService方式停止
    private val myBinder = MyBinder()

    override fun onCreate() {
        super.onCreate()
        Log.e("Service1", "onCreate: ")
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.e("Service1", "onStart: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("Service1", "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        Log.e("Service1", "onBind: ")
        return myBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("Service1", "onUnbind: ")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Service1", "onDestroy: ")
    }

    open inner class MyBinder : Binder() {
        fun getService(): Service {
            return this@Service1
        }
    }
}