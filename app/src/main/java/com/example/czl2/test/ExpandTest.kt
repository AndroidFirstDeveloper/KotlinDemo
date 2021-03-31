package com.example.czl2.test

/**czl20210223
扩展类
 */
class ExpandTest {

    public fun ExpandTest.testMyPrint() {

    }

    class User(val name: String, val age: Int)

    public fun User.method1() {
        println("调用User的扩展函数 name=$name")
    }

    fun test1() {
        var user = User("Tom", 11)
        user.method1()
    }

    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        var temp = this[index1]
        this[index1] = this[index2]
        this[index2] = temp
    }

    fun test2() {
        val list = mutableListOf<Int>(1, 2, 3, 4, 5)
        list.swap(2, 4)
        for (i in list) {
            println(i)
        }
    }

    open class C

    class D : C()

    fun C.foo() {
        println("C 扩展函数 foo")
    }

    fun D.foo() {
        println("D 扩展函数 foo")
    }

    fun test3(c: C) {
        c.foo()
    }

    /**类的扩展函数是静态解析的*/
    fun test4() {
        var c = D()
        test3(c)
    }

    class A {
        companion object {
            var number: Int = 10
        }
    }

    fun A.Companion.foo() {
        println("伴生对象的扩展函数foo")
    }

    /*为伴生对象设置扩展属性*/
    var A.Companion.phoneNumber: Int
        get() = number
        set(value) {
            number = value
        }

    fun test5() {
        A.foo()//调用伴生对象的扩展函数
        println("伴生对象的扩展属性")
        println(A.phoneNumber)//10
        A.phoneNumber = 20
        println(A.phoneNumber)//20
    }

    class E {
        init {
            var number: Int
        }

        var number1: Int = 10
    }

    public fun E.method1() {
        println("内部类的扩展函数")
    }

    /**由于扩展属性没有初始化器，所以没法进行初始化。同时扩展属性没有backing field，所以不能在get、set中使用field关键字
     *如果想要设置扩展属性的值，通过在扩展对象中定义一个公共属性，然后操作该属性的值间接的实现操作扩展属性的目的。
     * 这样的实现方式也不会对扩展类造成太大的影响，因为使用扩展属性也是需要定义该类的对象，虽然修改了类中原有的属性，但是对其它使用该类
     * 创建对象的地方没有影响
     * */
    var E.prices: Int
        get() = 10 + number1
        set(value) {
            number1 = value * 2
        }


    fun test6() {
        val e = E()
        e.method1()
        println("内部类的扩展属性")
        println(e.prices)//打印初始值20
        e.prices = 20//赋值
        println(e.prices)//打印操作后的值50
        split("http://www.baidu.com")
    }

    public fun ExpandTest.split(source: String?) {
        val results = source?.split(".") ?: "hello"
        if (results is String) {
            println("results is string")
        } else if (results is List<*>) {
            println("results is list")
            for (item in results) println(item)
        } else {
            println("results is null")
        }
    }

    class AA {
        fun method1() {
            println("类的函数")
        }

        fun test() {}
    }

    fun AA.method1() {
        println("类的扩展函数")
    }

    fun AA.method2() {
        test()//调用扩展类中的函数
        test8()//调用分发接收者类中的函数
        println("类的扩展函数")
    }

    fun test8() {
        println("分发接收者类中的函数")
    }

    /*当扩展函数跟扩展类中的函数相同时，优先调用扩展类中的函数*/
    fun test7() {
        val aa = AA()
        aa.method1()
        aa.method2()
    }

}