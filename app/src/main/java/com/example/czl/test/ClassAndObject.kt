package com.example.czl.test

/**类和对象
 * 主构造器的参数可以在初始化代码段中使用//这句话的意思是构造函数的参数可以再init代码段中使用
 * ，也可以在类主体定义的属性初始化代码中使用。//这句话的意思是构造函数的参数可以直接赋值给成员变量
 * 一种简洁语法，可以通过主构造器来定义属性并初始化属性值（可以是var或val）：
 * */
class ClassAndObject(name1: String, score1: Int, address1: String, phoneNumber1: String) {
    var name: String = name1
        get() = field.toUpperCase()
        set
    var score: Int = score1
        get() = field
        set(value) {
            if (value < 60) {
                field = 1
            } else {
                field = 2
            }
        }
    val address: String? = address1
        get() = field
    var phoneNumber: String = phoneNumber1
        get() = field.toString().replace('2', '*', true)
        set

    init {
        println("类的初始化代码段执行")
        println("name=$name1,score1=$score1,address1=$address1,phoneNumber1=$phoneNumber1")
    }

    /**可以通过主构造器来定义属性并初始化属性(一定要加上var或者val否则就不是定义属性了)*/
    class InnerClass(var price: Float, val date: String) {

    }

    /**如果类有主构造函数，每个次构造函数，都要直接或者间接通过另一个次构造函数代理柱构造函数。
    同一个类中代理另一个构造函数只需要通过this关键字*/
    class InnerClass2(age: Int) {

        constructor(name: String, age: Int) : this(age) {

        }
    }

    /**如果一个类没有显示的定义构造函数，那么编译器在编译的时候会自动创建一个公共的无参的构造函数*/
    class InnerClass3() {

    }

    /**如果你不想你的类有公共的构造函数，那你需要显示的声明一个私有的空主构造函数*/
    class InnerClass4 private constructor() {

    }

    /**注意：在 JVM 虚拟机中，如果主构造函数的所有参数都有默认值，编译器会生成一个附加的无参的构造函数，
    这个构造函数会直接使用默认值。这使得 Kotlin 可以更简单的使用像 Jackson 或者 JPA 这样使用无参构造函数来创建类实例的库。*/


    /**抽象类与实现类*/
    abstract class Base {
        abstract fun method1()

        fun method3() {
            println("Base 类中的非抽象方法method3")
        }
    }

    class BaseImpl : Base() {
        override fun method1() {
            TODO("Not yet implemented")
            println("BaseImpl 实现 Base类的method1方法")
        }
    }

    /**嵌套类相当于java的静态内部类*/
    class InnerClass5 {
        fun method1() {
            println("嵌套类")
        }
    }

    /**内部类相当于java的非静态内部类*/
    inner class InnerClass6 {
        fun method1() {
            println("内部类访问外部类的成员变量name=$name")
        }

        /**为了消除歧义，要访问来自外部作用域的 this，我们使用this@label，其中 @label 是一个 代指 this 来源的标签。*/
        fun method2() {
            val outerObj = this@ClassAndObject
            println(outerObj.phoneNumber)
        }
    }

    fun setListener(test: TestInterface) {

    }

    interface TestInterface {
        fun test()
    }

    /**
     * 采用对象表达式来创建接口对象，即匿名内部类的实例。
     */
    class InnerClass7 {
        fun method1() {
            val classAndObject = ClassAndObject("Janne", 100, "Washington", "1277383894");
            classAndObject.setListener(object : TestInterface {
                override fun test() {
                    TODO("Not yet implemented")
                    println("匿名内部类，通过对象表达式的方式实现")
                }
            })
        }
    }
    /**类的修饰符包括 classModifier 和_accessModifier_:

    classModifier: 类属性修饰符，标示类本身特性。

    abstract    // 抽象类
    final       // 类不可继承，默认属性
    enum        // 枚举类
    open        // 类可继承，类默认是final的
    annotation  // 注解类
    accessModifier: 访问权限修饰符

    private    // 仅在同一个文件中可见
    protected  // 同一个文件中或子类可见
    public     // 所有调用的地方都可见
    internal   // 同一个模块中可见*/
}