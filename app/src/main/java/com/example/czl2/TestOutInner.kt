package com.example.czl2

class TestOutInner {

    /**People相当于java中的非静态内部类，People会隐式持有外部类的引用*/
    inner class People {
        fun method1() {
            println("method1")
        }
    }

    /**查看字节码文件发现，嵌套类Child相当于java中的静态内部类*/
    class Child() {
        fun method2() {
            println("method2")
        }
    }
}