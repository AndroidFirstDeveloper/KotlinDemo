package com.example.czl.component

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.czl.R

class Fragment1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("Fragment1Activity", "onCreate: ")
        setContentView(R.layout.activity_fragment1)
        val fragment1 = Fragment1()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.activity_fragment1_container, fragment1)
        fragmentTransaction.commit()
    }

    override fun onStart() {
        super.onStart()
        Log.e("Fragment1Activity", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Fragment1Activity", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Fragment1Activity", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e("Fragment1Activity", "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Fragment1Activity", "onDestroy: ")
    }

    fun openActivity2(view: View) {
        val intent = Intent(Fragment1Activity@ this, Activity2::class.java)
        startActivity(intent)
    }
}