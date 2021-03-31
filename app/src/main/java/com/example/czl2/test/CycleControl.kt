package com.example.czl2.test

class CycleControl {
    fun forMethod() {
        val items = listOf<String>("apple", "bannane", "peach", "pair")
        for (item in items) {
            println(item)
        }
        for (i in items.indices) {
            println("item at $i is ${items[i]}")
        }
        for ((index, value) in items.withIndex()) {
            println("index=$index  value=$value")
        }
    }

    fun whileMethod() {
        var x = 5
        while (x > 0) {
            println(x--)
        }

        var condition = true
        do {
            condition = false
            println("while cycle contion=$condition")
        } while (condition)
    }

    fun forContinueAndBreak() {
        for (i in 1..10) {
            if (i == 3) continue
            println(i)
            if (i > 5) break
        }
    }

    fun forCycleLabel() {
        abc@ for (i in 1..4) {
            for (j in 1..6) {
                if (i == 2 && j == 2) {
                    break@abc
                } else {
                    println("i=$i j=$j")
                }
            }
        }

        for (i in 1..3) {
            loop@ for (j in 1..5) {
                if (j % 2 == 0) {
                    break@loop
                } else {
                    println("i=$i  j=$j")
                }
            }
        }
    }

    /**标签处返回
     * foreach中的return标签 其功能跟for循环中的continue相似，而不是退出整个foreach循环
     * */
    fun labelReturn1() {
        println("labelReturn1    start")
        val items = intArrayOf(1, 2, 3, 4, 5)
        items.forEach {
            if (it == 2) return
            println(it)
        }
        println("labelReturn1    end")
    }

    fun labelReturn2() {
        println("labelReturn2    start")
        val items = intArrayOf(1, 2, 3, 4, 5)
        items.forEach label2@{
            if (it == 1) return@label2
            println(it)
        }
        println("labelReturn2    end")
    }

    fun labelReturn3() {
        println("labelReturn3    start")
        val items = intArrayOf(1, 2, 3, 4, 5)
        items.forEach {
            if (it == 1) return@forEach
            println(it)
        }
        println("labelReturn3    end")
    }

    fun labelReturn4() {
        println("labelReturn4    start")
        val items = intArrayOf(1, 2, 3, 4, 5)
        items.forEach(fun(value: Int) {
            if (value == 1) return
            println(value)
        })
        println("labelReturn4    end")
    }
}