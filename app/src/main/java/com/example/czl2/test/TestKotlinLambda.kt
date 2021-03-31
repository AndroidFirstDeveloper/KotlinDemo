package com.example.czl2.test

import android.view.View
import android.widget.Toast

/**
测试Kotlin的“lambda表达式”
这里加引号，其实是想说明kotlin中的表达式，其实跟java中的表达式不太一样，kotlin中的表达式往往是一个函数类型的对象
高阶函数：函数的参数或者返回值是函数类型，这样的函数属于高阶函数
 */
class TestKotlinLambda {

    fun test1(view: View) {
        //当参数是函数的最后一个参数时，可以把lambda表达式放在括号的外面
        view.setOnClickListener() { v ->
            Toast.makeText(v.context, "hello", Toast.LENGTH_SHORT).show()
        }
        //当函数只有一个参数时，可以直接把括号去掉
        view.setOnClickListener { v ->
            Toast.makeText(v.context, "hello", Toast.LENGTH_SHORT).show()
        }
        //当没有用到lambda表达式中的参数的时候可以直接去掉
        view.setOnClickListener {
            Toast.makeText(view.context, "hello", Toast.LENGTH_SHORT).show()
        }

        val selectUser = SelectUser()
        selectUser.lookUserDetail(object : ItemSelectedListener {
            override fun select(view: View, index: Int, cancel: Boolean) {

            }
        })

        //声明一个函数类型的对象
        val methodObj1 = ::test2
        //将函数类型的对象引用赋给另一个对象
        val methodObj2 = methodObj1
        val result1 = methodObj1(1)//函数类型的对象调用函数的方式
        val result2 = methodObj2(2)
        println("$result1,$result2")

        //调用参数、返回值都是函数类型的函数
        val methodObj3 = ::test9
        val methodObj4 = test7(methodObj3, 1)
        val result3 = methodObj4(1, "nice")
        println("$result3")
    }

    //test3方法的第一个参数是函数类型的
    //函数的参数是函数类型.函数类型的参数或者返回值需要明确指明函数类型的对象有几个参数、每个参数的类型、返回值的类型
    //有三个参数类型是Int、String、Boolean，有返回值类型是String
    private fun test3(method1: (Int, String, Boolean) -> String, age: Int): String {
        method1(1, "hello", true)
        return age.toString()
    }

    //test4方法的第一个参数是函数类型的
    //没有参数，没有返回值的函数类型的参数
    private fun test4(method1: () -> Unit, age: Int): String {
        method1()
        return age.toString()
    }

    //test5方法返回值类型是函数类型
    //有三个参数类型是Int、String、Boolean，有返回值类型是String
    private fun test5(age: Int): (Int, String, Boolean) -> String {
        return ::test6
    }

    //test7方法的第一个参数类型是函数类型，返回值类型也是函数类型
    //test7第一个参数是有两个参数类型是Int、Boolean，有返回值类型是String；
    // test7返回值是有两个参数，类型是Int、String，返回值类型是Float的函数类型
    private fun test7(method1: (Int, Boolean) -> String, index: Int): (Int, String) -> Float {
        method1(1, true)
        println("$index")
        return ::test8
    }

    private fun test8(index: Int, name: String): Float {
        println("$index,$name")
        return index.toFloat()
    }

    private fun test9(index: Int, choice: Boolean): String {
        println("$index,$choice")
        return "how are you"
    }

    private fun test6(index: Int, name: String, select: Boolean): String {
        return "world"
    }

    private fun test2(index: Int): String {
        return index.toString()
    }

    class SelectUser {

        fun lookUserDetail(listener: ItemSelectedListener) {

        }
    }

    interface ItemSelectedListener {
        fun select(view: View, index: Int, cancel: Boolean)
    }
}