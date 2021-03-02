package com.example.czl.test

public class NumberType {

    fun useNumber() {
        var a: Int = 100
        var b: Long = 100000L
        var c: Float = 12.25f

        val d = 100_100
        val e: Long = 100_100_1000
        val f = 12.25_26_2
        println("a(int)=$a,b(long)=$b,c(float)=$c,d(int)=$d,e(long)=$e,f(float)=$f")//a(int)=100,b(long)=100000,c(float)=12.25,d(int)=100100,e(long)=1001001000,f(float)=12.25262
    }

    fun compareNumber() {
        //当a、b、c可为空的时候，不管是val还是var类型，b===c 为false，否则为true
        val a: Int = 1000
        println("a===a：${a === a}")
        val b: Int? = a
        val c: Int = a
        println("b===c：${b === c}")//结果跟b、c是否可为空有关，可为空的话false，不可为空true
        println("b==c：${b == c}")
    }

    fun numberChangeType() {
        var a: Short = 11
        var b: Byte = 2
        var c: Int = a.toInt()
        var d: Int = b.toInt()
        var e: Int = 1000000
        var f: Byte = e.toByte()
        println("a=$a,b=$b,c=$c,d=$d,e=$e,f=$f")//a=11,b=2,c=11,d=2,e=1000000,f=64

        var x: Char = 'a'
        if (x == 'a') {
            println("x==a")
        }
        x = 'd'
        if (x in 'a'..'c') {
            println("x in a..c")
        }
    }

    fun logicCalculate() {
        println("测试 || 逻辑运算1")
        if (print1() || print2()) {//3

        }
        println("测试 || 逻辑运算2")
        if (print2() || print1()) {//2

        }

        println("测试 && 逻辑运算1")
        if (print1() && print2()) {//2

        }
        println("测试 && 逻辑运算2")
        if (print2() && print1()) {//3

        }
        println("测试 ! 逻辑运算1")
        if (!print1()) {//3
            println("!print1() 返回false")
        }
        println("测试 ! 逻辑运算2")
        if (!print2()) {//2
            println("!print2() 返回false")
        }
    }

    fun print1(): Boolean {
        println("print1 method called")
        return false
    }

    fun print2(): Boolean {
        println("print2 method called")
        return true
    }

    fun arrayUse() {
        val array1 = arrayOf(1, 2, 3, 4)
        val array2 = Array(4) { i -> (i * 2) }
        val intArray = intArrayOf(1, 2, 3)
        val shortArray = shortArrayOf(1, 2, 3)

        println(array1[0])
        println(array2[3])
        println(intArray[0])
        println(shortArray[0])
    }

    fun stringUse() {
        val name: String = "Tom"
        for (c in name) {
            println(c)
        }
        val multiText = """
            第一行
            第二行
            第三行
        """
        println(multiText)
        println("--------------------------")
        val multiText2 = """
            第一行
            第二行
            第三行
        """.trimMargin()
        println(multiText2)

        //输出特殊符号
        println("牛奶的价格是$9.9")
    }

    fun testNullCheck() {
        var age: String? = "11"
        var result = age?.toInt()//对于可为空的对象在访问对象的方法、属性的时候需要处理对象为空的情况，否者编译就会报错。处理对象可能为空有两种方法：1、使用?. 对象为空的话返回null 2、使用!! 对象为空的话直接抛出异常
        var result2 = result.toString()
    }
}