package com.example.czl.test

/**测试kotlin中的枚举*/
class EnumTest {

    enum class Color {
        RED, BLUE, GREEN, BLACK
    }

    enum class Shape(val type: Int) {
        OVAL(1), CIRCLE(2), RECT(10), SEQURE(7);

        fun targetValue(type: Int): Shape? {
            for (item in values()) {
                if (item.type == type) {
                    return item
                }
            }
            return null
        }
    }

    /**枚举类中定义的常量跟方法之间直接必须用分号分隔
    枚举常量中的匿名内部类必须要实现枚举类的抽象方法*/
    enum class Student(val grade: Int) {
        LILI(1) {
            override fun alias() {
                println("xiao li")
            }
        },
        TOM(2) {
            override fun alias() {
                println("cat ")
            }
        };

        abstract fun alias()
    }


    fun testEnumUse() {
        val values = Color.values()
        for (item in values) {
            println("${item.name},${item.ordinal}")
        }
        val result = Color.valueOf("RED")//以枚举值的名称获取枚举，没有找到的话会抛出异常
        println("${result.name},${result.ordinal}")
        val values2 = Shape.values()
        for (item in values2) {
            if (item.type == Shape.CIRCLE.type) {
                println("${item.name},${item.ordinal},${item.type}")
                break
            }
        }
        val value3 = Student.values()
        for (item in value3) {
            item.alias()
        }
        val color = enumValueOf<Color>("BLACK")//通过泛型的方式访问枚举的常量，如果未找到常量的话会抛出异常
        println("${color.name},${color.ordinal}")
        println(enumValues<Color>().joinToString { it.name })//通过泛型的方式获取枚举类的所有常量
    }
}