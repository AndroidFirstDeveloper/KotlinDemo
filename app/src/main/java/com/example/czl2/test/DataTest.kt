package com.example.czl2.test
/**czl20210223
数据类定义测试*/
class DataTest {

    fun test1() {
        val person = Person("zhang san", 22)
        val person2 = person.copy(name = "li si")
        println(person)
        println(person2)
        val (name1, age1) = person2
        println("name=$name1,age=$age1")//多重赋值
        println(person2.component1())//解构声明
        println(person2.component2())
        val childStr = "10,Janne,120,3"
        val (age, name, tel, grade) = childStr.split(",")
        println("age=$age,name=$name,tel=$tel,grade=$grade")

        //使用系统自带数据类Pair、Triple
        val pair = Pair(200, "good morning")
        val triple = Triple("Tom", 11, 1.0f)
        println(pair.first)
        println(pair.second)
        println(triple.first)
        println(triple.second)
        println(triple.third)

        val (weight, note) = Pair(1000, "hard is success")
        println("weight=$weight,note=$note")
        val (name2, age2, score) = Triple("Lili", 8, 70)
        println("name2=$name2,age2=$age2,score=$score")
        val (name3, age3) = AB("zHOU", 11)//使用非数据类实现解构功能
        println("name3=$name3,age3=$age3")
    }

    /*定义非数据类AB，通过实现componentN方法，使该类具有解构功能*/
    class AB {
        constructor(name: String, age: Int) {
            this.name = name
            this.age = age
        }

        var name: String
            get() = field
            set(value) {
                field = value
            }
        var age: Int
            get() = field * 2
            set(value) {
                field = value
            }

        operator fun component1(): String {
            return this.name
        }

        operator fun component2(): Int {
            return this.age
        }
    }
}