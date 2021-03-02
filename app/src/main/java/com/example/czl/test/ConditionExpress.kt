package com.example.czl.test

class ConditionExpress {

    fun ifMethod() {
        var max: Int
        val a = 1
        val b = 4
        if (a > b)
            max = a

        if (a > b) {
            max = a
        } else {
            max = b
        }

        max = if (a > b) a else b

        max = if (a > b) {
            println("a>b")
            a
        } else {
            println("a<b")
            b
        }
        println("max=$max")
    }

    fun sectionMethod() {
        val x = 10
        if (x in 1..12) {
            println("$x in 1..12")
        }
    }

    /**when相当于switch语句*/
    fun whenMethod() {
        val type = 1
        when (type) {
            1 -> {
                println("审批中")
            }
            2 -> {
                println("已驳回")
            }
            3 -> {
                println("已撤销")
            }
            4 -> {
                println("已通过")
            }
            else -> {
                println("未知类型")
            }
        }

        val type2 = 2
        when (type2) {
            1, 2 -> {
                println("ImageViewHolder 类型")
            }
            3, 4, 5 -> {
                println("TextViewHolder 类型")
            }
            6 -> {
                println("ButtonViewHolder 类型")
            }
            else -> {
                println("默认的ViewHolder 类型")
            }
        }

        val type3 = 3
        when (type3) {
            in 1..2 -> {
                println("type in 1..2")
            }
            in 3..6 -> {
                println("type in 3..6")
            }
            7 -> {
                println("type equal 7")
            }
            else -> {
                println("type is $type")
            }
        }

        val type4: Any = 4
        when (type4) {
            is String -> {
                println("type is String")
            }
            is Int -> {
                println("type is Int")
            }
            is Boolean -> {
                println("type is Boolean")
            }
            else -> {
                println("type is null")
            }
        }
    }

    fun whenMethod2(type5: Int) = when (type5) {
        in 1..2 -> {
            "Type12ViewHolder"
        }
        3 -> {
            "Type3ViewHolder"
        }
        else -> {
            "DefaultViewHolder"
        }
    }
}