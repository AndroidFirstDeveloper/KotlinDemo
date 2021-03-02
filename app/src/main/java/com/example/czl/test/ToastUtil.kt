package com.example.czl.test

import android.app.Activity
import android.content.Context
import android.widget.Toast

public fun Toast.toast(context: Context, content: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, content, duration).show()
}

public fun Activity.recycleSource() {
    println("activity 回收资源")
}