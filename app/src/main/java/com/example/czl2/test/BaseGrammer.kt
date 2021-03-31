package com.example.czl2.test

/**基本语法：函数的定义*/
public class BaseGrammer {
    fun sum1(a: Int, b: Int): Int {
        return a + b
    }

    fun sum2(a: Int, b: Int) = a + b

    public fun sum3(a: Int, b: Int) = a + b

    fun sum4(a: Int, b: Int): Unit {
        println("sum4结果：" + (a + b))
    }

    fun sum5(a: Int, b: Int) {
        println("sum5结果：" + (a + b))
    }

    fun dynamicArgsMethod(vararg v: Int) {
        for (vt in v) {
            println("参数：$vt")
        }
    }

    fun lambdaMethod() {
        val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
        println("lambda表达式：${sumLambda(1, 2)}")
    }

    fun dynamicAndStaticVariable() {
        val a: Int = 1
        val b: Int
        b = 2
        val c = 1
        var d: Int = 1
        d = d * 2
        var e = 1
        e++

        println("a=$a")
        println("b=$b")
        println("c=$c")
        println("d=$d")
        println("e=$e")
    }

    fun notNullDeal() {
        var age: String? = "23"
        val age1 = age!!.toInt()
        val age2 = age?.toInt()
        val age3 = age?.toInt() ?: -1
        println("age：$age age1:$age1,age2:$age2,age3:$age3")
    }

    fun judgeArgsType(obj: Any): Int? {
        if (obj is String) {
            return obj.length
        }
        if (obj is Int) {
            return obj;
        }
        return null
    }

    fun cycleSpaceUse() {
        println("测试 i in 1..4")
        for (i in 1..4) {
            print(i)
        }
        println("\n测试 i in 4..1")
        for (i in 4..1) {//什么都没有输出
            print(i)
        }
        println("\n测试 i in 4 downTo 1")
        for (i in 4 downTo 1) {
            print(i)
        }
        println("\n测试 i in 1..4 step 2")
        for (i in 1..4 step 2) {
            print(i)
        }
        println("\n测试 i in 4 downTo 1 step 2")
        for (i in 4 downTo 1 step 2) {
            print(i)
        }
        println("\n测试 i in 1 until 4")
        for (i in 1 until 4) {
            print(i)
        }
        println("")
    }
}