package com.example.czl.test

/**kotlin测试对象表达式的使用*/
class TestObjectExpress {

    var counter: Int = 0

    /**对象表达式可以越过类的定义，直接得到一个对象
     *site 需要声明为private才可以被test1方法使用
     * */
    private val site = object {
        val name = "百度"
        val url = "www.baidu.com"
    }

    fun test1() {
        println("${site.name},${site.url}")
    }

    /*对象表达式创建的对象做为私有函数的返回类型是匿名对象的类型*/
    private fun test2() = object {
        val x: Int = 1
    }

    /*对象表达式创建的对象作为共有函数的返回类型是匿名类的超类型*/
    fun test3() = object {
        val y: String = "hello"
    }

    open class A(val age: Int) {

    }

    interface B {
        fun onItemChoice()
    }

    val ab: B = object : A(1), B {
        override fun onItemChoice() {
            println("接口B的抽象方法")
        }
    }

    fun testAB() {
        ab.onItemChoice()
    }

    interface OnItemSelectListener {
        fun onItemSelect(index: Int)
    }

    /**通过对象表达式创建一个接口的实现实例*/
    val listener = object : OnItemSelectListener {
        override fun onItemSelect(index: Int) {
            println(counter++)//在匿名内部类中可以正常访问作用域的函数或属性
            println(index)
        }
    }

    fun test4(listener: OnItemSelectListener) {
        listener.onItemSelect(10)
    }

    /**-----------------------------对象声明-----------------------------*/
    /*定义一个简单的对象声明*/
    object FileUtil {
        var name: String = ""
        var url: String = ""

        fun saveFile(path: String) {
            println("path=$path")
        }
    }

    private fun test5() {
        val instance1 = FileUtil
        val instance2 = FileUtil
        instance1.name = "baidu"
        println(instance2.name)
        instance1.saveFile("E://ALog/xfLog.txt")
    }

    abstract class Animal {
        abstract fun eat()
    }

    /**
     * 对象声明的类的特点：单例（从test6方法打印结果可以验证）
     * 无法访问外部类中的函数、属性
     * 可以继承其它类
     * 不能声明带参的构造函数（如果有构造函数那就可以创建对象了，跟单例的特点冲突）
     *
     * */
    //定义一个简单的对象声明，可以继承其它的类或接口
    object Boy : Animal() {
        var name: String = ""
        var age: Int = 0
        override fun eat() {
            println("$name 吃谷物，水果，蛋白质")
//            println(counter)
        }
    }

    private fun test6() {
        val boy1 = Boy
        val boy2 = Boy
        boy1.name = "Tom"
        boy1.eat()
        boy2.eat()
    }

    /**对象声明和对象表达式的区别：
    对象声明是在第一次被访问时延迟初始化的
    对象表达式是在使用的时候立即执行的
     */

    fun myTest() {
        test1()
        val value1 = test2().x
        println(value1)
        val value2 = test3()
        println(value2.toString())
        test4(listener)
        test5()
        test6()
    }
}