package com.example.czl.component

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import com.example.czl.R

class Activity1 : AppCompatActivity() {

//    onCreate1-onStart1-onResume1-onPause1-
//    onCreate2-onStart2-onResume2-
//    onStop1-
//    onPause2-
//    onStart1-onResume1-
//    onStop2-onDestroy2-
//    onPause1-onStop1-onDestroy-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)
        Log.e("Activity1", "onCreate: 1")
        if (savedInstanceState != null) {
            Log.e("Activity1", "onCreate1: " + savedInstanceState.getString("data"))
        }
    }

    /**
    在android9.0(API=28)之前，该方法在onStop之前执行，可能在onPause之前或之后执行
    在android9.0之后，该方法在onStop之后执行
    该方法每次都会执行，
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("Activity1", "onSaveInstanceState: 1")
        outState.putString("data", "hello world")
    }

    /**
    该方法只有在activity被系统异常销毁，并再次启动时调用
    该方法在onStart方法之后执行
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("Activity1", "onRestoreInstanceState1: " + savedInstanceState.getString("data"))
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("Activity1", "onRestart: 1")
    }

    override fun onStart() {
        super.onStart()
        Log.e("Activity1", "onStart: 1")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Activity1", "onResume: 1")
    }

    /**
     * 当activity处于后台的时候会调用该方法
     * activity不在栈顶
     * 当前页面弹出dialog、menu、popupwindow时不会调用该方法
     * */
    override fun onPause() {
        super.onPause()
        Log.e("Activity1", "onPause: 1")
    }

    override fun onStop() {
        super.onStop()
        Log.e("Activity1", "onStop: 1")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Activity1", "onDestroy: 1")
    }

    fun openDialog(view: View) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("选择对话框")
            .setMessage("请选择一个乘客")
            .setPositiveButton("确定", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0?.dismiss()
                }
            }).setNegativeButton("取消", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0?.dismiss()
                }
            }).create()
        dialog.show()
    }

    fun openPopupMenu(view :View){
        val popupMenu=PopupMenu(this,view)
        popupMenu.inflate(R.menu.mymenu)
        popupMenu.show()
    }

    fun openPopupWindow(view:View){
        val popupWindow=PopupWindow()
        popupWindow.contentView=LayoutInflater.from(this).inflate(R.layout.popup_window_layout,(view.parent) as LinearLayout,false)
        popupWindow.showAsDropDown(view)
    }

    fun openActivity(view: View) {
        val intent = Intent(Activity1@ this, Activity2::class.java)
        startActivity(intent)
    }
}