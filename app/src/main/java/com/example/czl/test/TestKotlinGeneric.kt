package com.example.czl.test

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

/**测试Kotlin中的泛型*/
class TestKotlinGeneric {

    fun test1(context: Context) {
        val producer1: Producer<TextView> = Producer<Button>(Button(context))
        val textView = producer1.getElement()
        val consumer1: Consumer<Button> = Consumer<TextView>()
        consumer1.setElement(Button(context))

        val list1: List<TextView> = List(10) { index: Int ->
            println("create $index object")
            TextView(context)
        }
        val list2: List<Button> = List(10) {
            Button(context)
        }
        val list3: List<View> = List(10) {
            View(context)
        }

        val list4: List<TextView> = list2

        val list5: List<out TextView> = list2
        val list6: List<out TextView> = list1
        //val list7: List<out TextView>=list3

        //测试下界通配符(类比java的 ? super T)
        //var listss:List<in Button>//编译报错，List不允许使用in
        val list1_1: MutableList<Button> = MutableList(10) {
            Button(context)
        }
        val list2_1: MutableList<TextView> = MutableList(10) {
            TextView(context)
        }
        val list3_1: MutableList<View> = MutableList(10) {
            View(context)
        }
        var list8: MutableList<in Button> = list1_1
        val list9: MutableList<in Button> = list2_1
        val list10: MutableList<in Button> = list3_1
        //val list11: MutableList<in TextView> = list1_1//编译报错，下界操作符限制元素必须是TextView的基类或接口
        //list10.add(TextView())//添加数据-编译报错，下界通配符不允许添加数据
        list10.add(Button(context))//添加数据，下界通配符只允许添加下界类型的对象
        //val element1: Button = list10.get(0)//获取数据-编译报错，下界通配符获取的元素类型只能是any类型，这点跟java中的object类似
        //val element2:TextView=list10.get(0)//获取数据-编译报错,下界通配符获取的元素类型只能是any类型
        val element3: Any? = list10.get(0)


        //测试上界通配符(类比java ? extends T)
        val mutableList1: MutableList<Button> = MutableList(10) {
            Button(context)
        }
        val mutableList2: MutableList<TextView> = MutableList(10) {
            TextView(context)
        }
        val mutableList3: MutableList<View> = MutableList(10) {
            View(context)
        }
        val list12: MutableList<out TextView> = mutableList1
        val list13: MutableList<out TextView> = mutableList2
        //val list14: MutableList<out Button> = mutableList3//编译报错，上界操作符限制元素必须是TextView的子类
        //list12.add(Button(context))//添加数据-编译报错，泛型通配符不允许添加数据
        //list12.add(TextView(context))//编译报错，泛型通配符不允许添加数据。这个跟java是一样的
        //val data1: Button = list12.get(0)//获取数据-编译报错，获取对象只能是上界类型的
    }

    class Producer<out T> {
        private val t: T

        constructor(t: T) {
            this.t = t
        }

        fun getElement(): T {
            return this.t
        }
    }

    class Consumer<in T> {

        constructor() {

        }

        fun setElement(t: T) {
        }
    }
}