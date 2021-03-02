package com.example.czl.test

/*集合测试*/
class ListTest {

    /**kotlin创建不可变集合*/
    fun testCreateIMMutableList() {
        println("测试不可变集合1")
        var list1 = listOf<Int>(1, 2, 3, 4)
        var list2 = emptyList<String>()//空集合
        var list3 = listOfNotNull(1, 2, null)//集合中有两个元素,该函数会自动去掉传入的nul,所以打印结果只有两个元素
        var list4: List<Int> = listOf()//空集合

        println(list1)
        println(list2)
        println(list3)
        println(list4)
    }

    /**kotlin创建可变集合*/
    fun testCreateMutableList() {
        println("测试可变集合2")
        var list1 = mutableListOf<Int>(1, 2, 3, 4)
        var list2 = arrayListOf<String>()//空集合

        println(list1)
        println(list2)
    }

    /**kotlin测试不可变集合添加元素（不可变元素其实是不能添加元素的，添加元素其实是创建了新的集合）*/
    fun testIMMutableAddElement() {
        println("测试不可变集合添加元素")
        var list = listOf<Int>(1, 2, 3)
        println(list)
        list = list.plus(4)
        println(list)
        list += 5
        println(list)
    }

    /**kotlin测试可变集合添加元素*/
    fun testMutableAddElement() {
        println("测试可变集合添加元素")
        var list = mutableListOf<Int>(1, 2, 3)
        println(list)
        list.add(4)
        list.plus(5)
        println(list)

        var list2 = arrayListOf<Int>(1, 2, 3)
        println(list2)
        list2.add(4)
        list2.plus(5)
        println(list2)
    }

    /*kotlin 测试集合元素的填充*/

    /**kotlin测试集合与数组的转换*/
    fun testArrayAndListChange() {
        println("测试集合与数组的相互转换")
        var intArr = arrayOf(1, 2, 3)
//        println(intArr)//数组直接打印的话，打印的是对象的地址
        for (item in intArr) {
            print("$item,")
        }
        println()
        var list1 = intArr.asList()
        println(list1)
        var list2 = listOf(1, 2, 3)
        println(list2)
        var intArr2 = list2.toTypedArray()
        for (item in intArr2) {
            print("$item,")
        }
        println()
    }

    fun testRealUse() {
        println("测试集合、数组在实际开发中的应用")
        var targetStr = "192.168.10.222"
        println(targetStr)
        var list = targetStr.split(".")
        println(list)
        var strArr = list.toTypedArray()
        for (item in strArr) {
            print("$item,")
        }
        println()
        var list2 = listOf<String>("Tom", "Jane", "Machel", "Liming")
        var index = list2.indexOf("Machel")//如果找到的话返回元素的索引
        println("machel index is $index")
        var index2 = list2.indexOf("Zhou")//如果未找到的话返回-1
        println("Zhou index is $index2")
        var list3 = listOf<Int>(1, 2, 3, 4, 5, 6)
        var list4 = list3.subList(0, 4)//截取集合，包含0，不包含4
        println(list4)
    }


    /**kotlin集合排序sort、sortWith、sortDescending、sortBy*/
    fun testListSort() {
        var list1 = listOf<Int>(1, 3, 2, 9, 6, 5, 4, 8, 7)
        var list2 = list1.sorted()//对集合升序排序，默认升序(对于不可变集合，返回结果为排序结果，原有集合顺序不变)
        println(list1)
        println(list2)
        var list3 = list1.reversed()//对集合置反
        println(list1)
        println(list3)
        var list4 = list1.sortedDescending()//对集合进行降序排序
        println(list1)
        println(list4)
        var list11 = listOf<Fruit>(
            Fruit(1.0f, "Apple", 10),
            Fruit(5.0f, "Orange", 10),
            Fruit(2.0f, "Peach", 10)
        )
        var list5 = list11.sortedBy { fruit -> fruit.price }//对集合按照指定字段升序排序
        println(list11)
        println(list5)
        var compartor = object : Comparator<Int> {
            override fun compare(p0: Int?, p1: Int?): Int {
//                print("p0=$p0,p1=$p1")
                return (p0 ?: 0) - (p1 ?: 0)//对集合元素升序排序
            }
        }
        var list6 = list1.sortedWith(compartor)
        println(list1)
        println(list6)
        var comparator2 = object : Comparator<Int> {
            override fun compare(p0: Int?, p1: Int?): Int {
                return (p1 ?: 0) - (p0 ?: 0)//对集合元素降序排序
            }
        }
        var list7 = list1.sortedWith(comparator2)
        println(list1)
        println(list7)
        println("使用list自有的sortedByDescending降序排序")
        var list12 = listOf<Fruit>(
            Fruit(1.0f, "Apple", 10),
            Fruit(5.0f, "Orange", 10),
            Fruit(2.0f, "Peach", 10)
        )
        var list8 = list12.sortedByDescending { fruit -> fruit.price }//对元素指定属性降序排序
        println(list12)
        println(list8)
        //多条件排序sortWith(compareBy({},{},{}...)) 默认是升序排序
        println("使用compareBy方法进行多条件排序")
        var list13 = listOf<Fruit>(
            Fruit(1.0f, "Pair", 10),
            Fruit(1.0f, "Apple", 10),
            Fruit(5.0f, "Orange", 10),
            Fruit(2.0f, "Peach", 10)
        )
        var list9 = list13.sortedWith(compareBy({ it.price }, { it.name }))//先对price属性升序，在对name属性升序
        println(list13)
        println(list9)
        var list14 = listOf<Fruit>(
            Fruit(1.0f, "Pair", 10),
            Fruit(1.0f, "Apple", 10),
            Fruit(5.0f, "Orange", 10),
            Fruit(2.0f, "Peach", 10)
        )
        //实现接口的方式，非常重要的一种写法
        println("使用Comparator类进行排序")
        var comparator3: Comparator<Fruit> = Comparator { t, t2 ->
            if (t2.price == t.price) {
                t.name.compareTo(t2.name)
            } else {
                (t2.price - t.price).toInt()
            }
        }
        var list10 = list14.sortedWith(comparator3)//先对price属性降序，再对name属性升序
        println(list14)
        println(list10)
        println("对数据类进行排序")
        var animalList = listOf<Animal>(
            Animal("Horse", 500, "America"),//3
            Animal("Lion", 1000, "Africa"),//1
            Animal("Tiger", 800, "Russia"),//2
            Animal("Panda", 500, "China")//4
        )
        var sortAnimalList = animalList.sorted()
        println(animalList)
        println(sortAnimalList)
    }


    class Fruit(val price: Float, val name: String, val weight: Int) {
        override fun toString(): String {
            return "[price=$price,name=$name,weight=$weight]"
        }
    }

    /**数据类实现comparable接口进行排序，用户可以自己定义排序规则。这里的排序规则是按照体重降序，名称升序*/
    data class Animal(val name: String, val weight: Int, val country: String) : Comparable<Animal> {
        override fun compareTo(other: Animal): Int {
            if (other.weight == this.weight) {
                return this.name.compareTo(other.name)
            } else {
                return other.weight - this.weight
            }
        }
    }

    /**kotlin测试操作元素的方法*/
    fun testListConductElement() {
        var list1 = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        println(list1.any { it % 2 == 0 })//判断集合中是有偶数元素,有的话返回true
        println(list1.all { it % 2 == 0 })//判断集合中所有的元素都满足
        println(list1.count { it % 2 == 0 })//判断集合中满足条件的元素的个数
        println(list1.fold(100) { total, next -> total + next })//从左往右累加集合中的元素并加上初始值100
        println(list1.foldRight(100) { total, next -> total + next })//从右往左累加集合中的元素并加上初始值
        list1.forEach { value -> if (value % 2 == 0) print("$value,") }//遍历集合中的元素
        println()
        list1.forEachIndexed { index, value -> if (value % 2 == 0) print("($index,$value),") }//遍历集合中的元素，并输出索引
        println()
        println(list1.max())//查询集合中最大元素
        println(list1.maxBy { it + 11 })//获取方法处理后返回结果最大值对应的那个元素的初始值，如果没有则返回null；
        println(list1.min())
        println(list1.minBy { it + 11 })//获取方法处理后返回结果最小值对应那个元素的初始值，如果没有则返回null；
        println(list1.none { it % 3 == 0 })//判断集合中的元素是否都不满足条件，是的话返回true
        println(list1.reduce { total, next -> total + next })//与fold没有很大区别，唯一不同是没有初始值，返回结果也是从左往右累加集合中的元素
        println(list1.reduceRight { total, next -> total + next })//与foldRight没有很大区别，只是没有初始值而已
        println(list1.sumBy { it % 2 })//获取方法处理后返回结果的总和
    }

    /**kotlin测试过滤集合元素*/
    fun testFilterListElements() {
        println("测试过滤集合元素")
        var list1 = listOf<Int>(2, 5, 1, 8, 4, 7, 3, 6, 10, 9)
        println(list1.drop(4))//返回去掉集合前n个元素后剩余元素集合
        println(list1.dropWhile { it > 1 })//从左向右，返回第一个不满足条件的元素的索引之后的元素
        println(list1.dropLast(4))//返回去掉集合后n个元素后剩余元素集合
        println(list1.dropLastWhile { it > 5 })//从右向左，返回第一个不满足条件的元素位置之前的元素
        println(list1.filter { it % 2 == 0 })//过滤掉所有不满足条件的元素
        println(list1.filterNot { it % 2 == 0 })//过滤掉所有满足条件的元素
        println(list1.filterNotNull())//过滤掉所有为null的元素
        println(list1.take(3))//返回从第一个元素开始的n个元素
        println(list1.takeLast(3))//返回从最后一个元素开始的n个元素
        println(list1.takeWhile { it > 1 })//从左往右顺序，返回第一个不满足条件的元素索引前的所有元素
    }

    /**测试kotlin映射功能*/
    fun testListMapConduct() {
        var list1 = listOf<Int>(1, 2, 3, 4, 5, 6)
        println(list1.flatMap { listOf(it, it + 1) })
        println(list1.groupBy { if (it % 2 == 0) "even" else "odd" })//对元素按照条件分组
        println(list1.map { it * 2 })//对元素计算后生成一个新的集合
        println(list1.mapIndexed { index, value -> value * index })//对元素计算后生成一个新的集合，表达式附带索引
        println(list1.mapNotNull { it * 2 })//在对元素计算之前先过滤掉为空的，然后将计算结果生成为一个新的集合
    }

    /**测试kotlin查找元素*/
    fun testListFindElement() {
        var list = listOf<Int>(1, 2, 3, 4, 5)
        println(list.contains(4))//集合包含指定元素返回true
        println(list.elementAt(3))//查找对应索引的元素，索引越界会抛出异常
        println(list.elementAtOrNull(10))//查找对应索引的元素，索引越界会返回null
        println(list.first { it == 5 })//返回符合条件的第一个元素，没有符合条件的会抛出异常
        println(list.firstOrNull { it > 10 })//返回符合条件的第一个元素，没有符合条件的返回null
        println(list.indexOfFirst { it % 2 == 0 })//返回符合条件的第一个元素的下标，没有符合的返回-1
        println(list.indexOfLast { it % 2 == 0 })//返回符合条件的最后一个元素的索引，没有符合条件的返回-1
        println(list.indexOfFirst { it > 10 })//返回符合条件的最后一个元素的索引，没有符合条件的返回-1
        println(list.last { it % 2 == 0 })//返回符合条件的最后一个元素，没有符合条件的会抛出异常
        println(list.lastOrNull { it > 10 })//返回符合条件的最后一个元素，没有符合条件的返回null
        println(list.single { it == 2 })//返回符合条件的单个元素，如果没有符合或超过一个会抛出异常
        println(list.singleOrNull { it > 10 })//返回符合条件的单个元素，如果没有符合或超过一个会返回null
    }
}