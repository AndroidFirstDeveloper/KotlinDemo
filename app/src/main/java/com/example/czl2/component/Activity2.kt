package com.example.czl2.component

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.czl2.R

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        Log.e("Activity2", "onCreate: 2")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("Activity2", "onSaveInstanceState: 2")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("Activity2", "onRestoreInstanceState: 2")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("Activity2", "onRestart: 2")
    }

    override fun onStart() {
        super.onStart()
        Log.e("Activity2", "onStart: 2")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Activity2", "onResume: 2")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Activity2", "onPause: 2")
    }

    override fun onStop() {
        super.onStop()
        Log.e("Activity2", "onStop: 2")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Activity2", "onDestroy: 2")
    }
}