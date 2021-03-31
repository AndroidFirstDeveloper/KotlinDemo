package com.example.czl2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.czl2.test.Child
import com.example.czl2.test.Girl
import com.example.czl2.test.Young

/**测试MainActivity页面传递过来的Parcelable数据*/
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initData()
    }

    private fun initData() {
        if (intent != null) {
            val child = intent.getParcelableExtra<Child>("data")
            println(child?.toString() ?: "child 传递过来的数据为null")
            val young = intent.getParcelableExtra<Young>("data2")
            println(young?.toString() ?: "young 传递过来的数据为null")
            val girl = intent.getParcelableExtra<Girl>("data3")
            println(girl?.toString() ?: "girl 传递过来的数据为null")
        }
    }
}