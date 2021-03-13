package com.example.czl.component

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.czl.R

class Service1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service1)
    }

    fun start1(view: View) {
        Log.e("Service1Activity", "start1: ")
        val intent = Intent(Service1Activity@ this, Service1::class.java)
        startService(intent)
    }

    fun stop1(view: View) {
        Log.e("Service1Activity", "stop1: ")
        val intent = Intent(Service1Activity@ this, Service1::class.java)
        stopService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Service1Activity", "onDestroy: ")
    }
}