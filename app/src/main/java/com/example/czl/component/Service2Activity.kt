package com.example.czl.component

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import com.example.czl.R

class Service2Activity : AppCompatActivity() {

    private val connection = MyConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service2)
    }

    fun bind1(view: View) {
        Log.e("Service2Activity", "bind1: ")
        val intent = Intent(Service2Activity@ this, Service1::class.java)
        bindService(intent, connection, Service.BIND_AUTO_CREATE)
    }

    fun unbind1(view: View) {
        Log.e("Service2Activity", "unbind1: ")
        unbindService(connection)
    }

    fun stop1(view: View) {
        Log.e("Service2Activity", "stop1: ")
        val intent = Intent(Service2Activity@ this, Service1::class.java)
        stopService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Service2Activity", "onDestroy: ")
    }

    inner class MyConnection : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            Log.e("MyConnection", "onServiceConnected: ")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.e("MyConnection", "onServiceDisconnected: ")
        }
    }
}