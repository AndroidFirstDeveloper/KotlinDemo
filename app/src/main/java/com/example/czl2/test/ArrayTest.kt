package com.example.czl2.test

/*czl20210223
测试数组的常用功能*/
class ArrayTest {

    /**kotlin为基本数据类型创建了特殊的数组构建类和函数*/
    fun baseTypeArray() {
        val shortArr = shortArrayOf(1, 2, 3, 4)
        val byteArr = byteArrayOf(1, 2, 3, 4)
        val intArr = intArrayOf(1, 2, 3, 4, 5)
        val longArr = longArrayOf(1L, 2L, 3L, 4L, 5L)
        val floatArr = floatArrayOf(1.0f, 2.0f, 3.0f, 4.0f)
        val doubleArr = doubleArrayOf(1.0001, 2.00002, 3.00003)
        val booleanArr = booleanArrayOf(true, false, true, true, false)
        println("-------------遍历intArray1")
        for (item in intArr) {
            println(item)
        }
    }

    /**kotlin 为基本数据类型提供了可以指定数组大小的构建类*/
    fun baseTypeFixSizeArray() {
        val byteArr = ByteArray(4)
        val shortArr = ShortArray(4)
        val intArr = IntArray(4)
        val longArr = LongArray(4)
        val floatArr = FloatArray(4)
        val doubleArr = DoubleArray(4)
        val booleanArr = BooleanArray(4)
        println("-------------遍历intArray2")
        for (item in intArr) {
            println(item)
        }
    }

    /**kotlin 为所有类型提供了公共的构建数组的方法*/
    fun allTypeArray() {
        val intArr = arrayOf(1, 2, 3, 4)
        val floatArr = arrayOf(1.0f, 2.0f, 3.0f)
        val longArr = arrayOf(1L, 2L, 3L)
        val booleanArr = arrayOf(true, false, true, false)
        val stringArr = arrayOf("Tom", "Lili", "Janne")
        println("-------------遍历intArray3")
        for (item in intArr) {
            println(item)
        }
    }

    /**kotlin为 所有类型 提供了公共的构建固定大小数组的方法*/
    fun allTypeFixSizeArray() {
        val intArr = arrayOfNulls<Int>(3)
        val floatArr = arrayOfNulls<Float>(3)
        val longArr = arrayOfNulls<Long>(3)
        val booleanArr = arrayOfNulls<Boolean>(3)
        val stringArr = arrayOfNulls<String>(3)
        println("-------------遍历intArray4")
        for (item in intArr) {
            println(item)
        }
    }

    /**kotlin还未 所有类型 提供了默认为空(大小为0)的构建函数*/
    fun allTypeEmptyArray() {
        val intArr = emptyArray<Int>()
        val floatArr = emptyArray<Float>()
        val longArr = emptyArray<Long>()
        val booleanArr = emptyArray<Boolean>()
        val stringArr = emptyArray<String>()
        println("-------------遍历intArray5")
        for (item in intArr) {
            println(item)
        }
    }

    /**kotlin中访问数组元素既可以通过下标访问，也可以通过get方法访问*/
    fun traversalArrayElement() {
        println("-------------遍历intArray6")
        val intlist = arrayOf(1, 2, 3, 4, 5)
        /*类似java中的foreach*/
        for (item in intlist) {
            println(item)
        }
        /*类似java中的int i=0;i<n;i++ 方式访问，通过下标访问*/
        for (index in intlist.indices) {
            println(intlist[index])
        }
        /*类似java中的int i=0;i<n;i++ 方式访问，通过get方法访问*/
        for (index in intlist.indices) {
            println(intlist.get(index))
        }
    }

    /**kotlin中修改数组元素可以通过下标修改，也可以通过set方法修改*/
    fun modifyArrayElement() {
        println("-------------遍历intArray7")
        val intlist = arrayOf(1, 2, 3, 4, 5)
        for (index in intlist.indices) {
            intlist[index] = intlist[index] + 1
        }
        for (index in intlist.indices) {
            intlist.set(index, 10)
        }
    }


    /**kotlin中二维数组的创建+遍历*/
    fun traversalTwoArray() {
        println("-------------遍历intArray8")
        var counter: Int = 0
        //为二维数组赋值
        val intArr = Array(4) { arrayOfNulls<Int>(3) }
        for (i in intArr.indices) {
            print("i=$i,")
            for (j in intArr[i].indices) {
                print("j=$j,")
                intArr[i][j] = counter++
            }
            println()
        }
        //打印二维数组中的值
        for (one in intArr) {
            for (two in one) {
                println(two)
            }
        }
    }
    /*由于三维数组在开发中很少用到，所以暂时不进行实际测试了*/

    /**测试多种数据类型的数组*/
    fun testMultiTypeArray() {
        println("测试多中数据类型数组9")
        val multiTypeArr = arrayOf("1", 2, 3, 4.0f, true)
        for (item in multiTypeArr) {
            println(item)
        }
    }

    /**kotlin数组元素的扩充*/
    fun elementAdd() {
        println("添加数组元素10")
        var myArray = arrayOf(1, 2, 3, 4)
        for (item in myArray) {
            print("$item,")
        }
        println()
        myArray = myArray.plus(5)
        for (item in myArray) {
            print("$item,")
        }
    }

    /**kotlin填充数组:
     * fill方法的源码部分调用的是java代码，代码逻辑就是遍历数组，然后替换指定索引位置的元素值
     * */
    fun elementFill() {
        println()
        println("填充数组11")
        var myArray = arrayOf(1, 2, 3, 4)
        for (item in myArray) {
            print("$item,")
        }
        myArray.fill(111, 0, 2)//替换索引0-2的元素为111，包括0，不包括2
        println()
        for (item in myArray) {
            print("$item,")
        }
        println()
    }
}