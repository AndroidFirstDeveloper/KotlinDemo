package com.example.czl2.test

class ExtendTest {


    /**kotlin中的类默认是final的，想要实现继承的话需要显示声明为open*/
    open class A(name: String) {

    }

    /**子类有主构造函数，必须在子类构造函数调用父类的构造函数*/
    class B(name: String, age: Int) : A(name) {

    }

    /**子类没有主构造函数，必须在次级构造函数调用父类构造函数，使用super关键字*/
    class C : A {
        constructor(name: String, weight: Int) : super(name) {

        }

        constructor(name: String, age: Int, weight: Int) : super(name) {

        }
    }

    /**类以及它的方法默认是final的，所以想要继承或者重写，需要加上open关键字*/
    open class M {
        open fun method1() {
            println("M class method1")
        }

        fun method2() {
            println("M class method2")
        }
    }

    /**接口竟然还能有实现方法
     * 接口以及它的方法默认都是open的，所以可以直接实现、重写
     * */
    interface N {
        fun method1() {
            println("N class method1")
        }

        fun test() {
            println("this is N interface test method")
        }

        fun method3()
    }

    /**
     * 方法重写
     * 一个类的父类（类+接口）有多个方法是相同的：同名、参数相同，那么子类必须要重写该方法*/
    class Q : M(), N {
        override fun method1() {
            super<M>.method1()
            super<N>.method1()
        }

        override fun method3() {
            TODO("Not yet implemented")
        }
    }

    /**重写属性到底有什么用呢
     * 接口中的属性只能是抽象的，不允许初始化值，接口不会保存属性值，实现接口时，必须重写属性：
     * */
    interface Sale {
        val price: Float
    }

    class Apple : Sale {
        override var price: Float = 0f
            get() = field //TODO ("Not yet implemented")
            set(value) {
                field = value + 0.5f
            }
    }


    interface A1 {
        fun method1() {
            println("A1 method1")
        }

        fun method2()
    }

    interface A2 {
        fun method1() {
            println("A2 method1")
        }

        fun method2() {
            println("A2 method1")
        }

        fun method3()

        fun method4() {
            println("A2 method4")
        }
    }

    /**当被实现的接口中定义的方法已经实现且没有跟其它接口中的方法相同时子类不需要强制重写该方法
     * 比如method4
     * */
    class AImpl : A1, A2 {
        /**当父类中有相同方法的时候，子类必须要实现该方法，否则编译无法通过*/
        override fun method1() {
            super<A1>.method1()
            super<A2>.method1()
        }

        /**重写的method2是A1中的方法，如果不重写会报错。
        重写method2跟method1默认生成的代码不同，应该是因为A1.method2未实现而A2.method2实现了，method1在A1、A2都已经实现
        在下面的方法中输入super<A1>.method2()，编译会报错，因为该方法就是对A1.method2实现
        super.method2()方法调用的是A2.method2
         */
        override fun method2() {
            super.method2()
        }

        override fun method3() {
            TODO("Not yet implemented")
        }
    }
}