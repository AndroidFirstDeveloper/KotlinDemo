package com.example.czl2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * czl20210226RecyclerView页面
 * 测试kotlin中rv的真实使用效果
 * */
class RVActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_r_v)
        val activity_r_v_rv = findViewById<RecyclerView>(R.id.activity_r_v_rv)
        activity_r_v_rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = RVAdapter(getGoodsData())
        activity_r_v_rv.adapter = adapter
    }

    private fun getGoodsData(): List<GoodsModel> {
        return listOf(
            GoodsModel("牙膏", 7.5f, "2021-1-16"),
            GoodsModel("牙刷", 12.0f, "2021-1-16"),
            GoodsModel("洗面奶", 69.9f, "2021-1-16"),
            GoodsModel("大宝.SOD蜜", 9.5f, "2021-1-16"),
            GoodsModel("海飞丝", 49.9f, "2021-1-16"),
            GoodsModel("沐浴露", 59.5f, "2021-1-16"),
            GoodsModel("蓝月亮", 29.5f, "2021-1-16"),
            GoodsModel("雕牌清洁剂", 9.5f, "2021-1-16"),
            GoodsModel("老干妈", 9.9f, "2021-1-16"),
            GoodsModel("干果1", 9.9f, "2021-1-16"),
            GoodsModel("干果2", 9.9f, "2021-1-16"),
            GoodsModel("干果3", 9.9f, "2021-1-16"),
            GoodsModel("干果4", 9.9f, "2021-1-16"),
            GoodsModel("干果5", 9.9f, "2021-1-16"),
            GoodsModel("干果6", 9.9f, "2021-1-16"),
            GoodsModel("干果7", 9.9f, "2021-1-16"),
            GoodsModel("干果8", 9.9f, "2021-1-16")
        )
    }
}