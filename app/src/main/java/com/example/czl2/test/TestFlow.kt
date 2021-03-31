package com.example.czl2.test

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**测试协程中的流：Flow*/
class TestFlow {

    /**
     * 流的取消3
     * cancellable操作符可以对asFlow构建器创建的流在每次发射的时候都检测取消(流取消后没有抛出异常)
     * */
    fun test16() {
        CoroutineScope(Dispatchers.Main).launch {
            launch(Dispatchers.IO) {
                (1..5).asFlow().cancellable().collect { value ->
                    if (value == 3) cancel()
                    println(value)
                }
            }
        }
    }

    /**
     * 流的取消2
     *asFlow构建器创建的流在发射的时候不会检测流是否取消，而是在流结束的时候会检测(运行结果跟文档不太一样，没有抛出异常)
     * */
    fun test15() {
        CoroutineScope(Dispatchers.Main).launch {
            launch(Dispatchers.IO) {
                (1..5).asFlow().collect { value ->
                    if (value == 3) cancel()
                    println(value)
                }
            }
        }
    }

    /**
     * 流的取消1
     * flow构建器创建的流，每次发射流的时候都会检测流是否取消，取消后流不再继续收集(程序运行结果跟文档描述的不太一样，没有抛出异常)
     * */
    fun test14() {
        CoroutineScope(Dispatchers.Main).launch {
            launch(Dispatchers.IO) {
                sample9().collect { value ->
                    if (value == 3) cancel()
                    println(value)
                }
                println()
            }
        }
    }

    private fun sample9(): Flow<Int> = flow {
        for (i in 1..5) {
//            delay(100)
            println("Emit $i")
            emit(i)
        }
    }


    /**
     *流正常/异常 完成
     * onCompletion操作符的优点是其lambda表达式中的可空参数Throwable，可以判断流是正常完成还是异常完成
     * */
    fun test13() {
        CoroutineScope(Dispatchers.Main).launch {
            launch(Dispatchers.IO) {
                /*//try-finally 1正常结束
                try {
                    (1..5).asFlow().collect { value -> print("$value,") }
                } finally {
                    println()
                    println("try-finally done")
                }
                println()
                //complemetion 2正常结束
                (1..5).asFlow()
                    .onCompletion {
                        println()
                        println("onCompletion done")
                    }
                    .collect { value -> print("$value,") }*/
                println()
                //oncompletion 3异常结束
                sample8().onCompletion { cause ->//当有异常发生的时候，cause为null
                    if (cause != null) {
                        println()
                        println("onComplement happen exception")//先打印
                    }
                }.catch { e ->
                    println("Caught $e")//后打印
                }.collect { value -> print("$value,") }
            }
        }
    }

    /**
     * 声明式捕获异常
    我们可以将 catch 操作符的声明性与处理所有异常的期望相结合，将 collect 操作符的代码块移动到 onEach 中，并将其放到 catch 操作符之前
     */
    fun test12() {
        CoroutineScope(Dispatchers.Main).launch {
            launch(Dispatchers.IO) {
                (1..10).asFlow().onEach { value ->
                    check(value <= 5) { "Collect $value" }
                    println("$value")
                }.catch { e -> println("Caught $e") }//catch操作符捕获到了上述异常
                    .collect { }
            }
        }
    }

    /**异常透明性：不允许在发射器中用try-catch包裹发射代码，目的是为了让异常暴露给流的外部
     * 但是为了能够在收集器之前对发射器出现的异常做一个中间处理，kotlin提供了catch操作符
     * catch操作符只能捕获到发射器中的异常，对于收集器中的异常是捕获不到的
     * 注意：流中的异常需要进行显示的捕获，否则一旦出现异常，程序直接崩溃
     * */
    fun test11() {
        CoroutineScope(Dispatchers.Main).launch {
            launch(Dispatchers.IO) {
                sample8().catch { e -> emit("Caught $e") }
                    .collect { value -> println(value) }//1 在catch操作符处理一下异常()
                sample8().catch { emit("response -1") }
                    .collect { value -> println(value) }//2 在catch操作符化解异常(测试一下程序是否还能继续运行下去).结果证明程序还是会结束运行，并只打印了一次response -1
                (1..10).asFlow().catch { e -> println("$e") }.collect { value ->
                    check(value <= 5) { "Collect $value" }
                    println(value)
                }//3 测试catch操作符是否能捕获下游的异常.结果证明下游异常未被捕获，应用直接崩溃，闪退
            }
        }
    }

    /**
     * 流异常，捕获收集器中的异常
     * */
    fun test10() {
        CoroutineScope(Dispatchers.Main).launch {
            //捕获收集器中发生的异常
            try {
                (1..10).asFlow().collect { value ->
                    check(value <= 5) { "Collected $value" }//异常的描述信息
                    println("$value")
                }
            } catch (e: Throwable) {
                println("Catch $e")
            }
        }
    }

    /**
     * 流异常，捕获发射器中出现的异常
     * */
    fun test9() {
        CoroutineScope(Dispatchers.Main).launch {
            launch(Dispatchers.IO) {
                try {
                    sample8().collect { value -> println(value) }
                } catch (e: Throwable) {
                    println("Catch $e")
                }
            }
        }
    }

    private fun sample8(): Flow<String> = flow {
        for (i in 1..10) {
            emit(i)
        }
    }.map { value ->
        check(value <= 5) { "Collect $value" }//异常的描述信息
        "response $value"
    }

    /**
     * 流展平操作符测试
     * 流的结果看起来跟集合、序列是很相似的，有些操作也是这样的，但是并不是完全一样的，比如集合的展平(flatMap、flatten)操作符在流中就无法使用
     * */
    fun test8() {
        CoroutineScope(Dispatchers.Main).launch {
            launch(Dispatchers.IO) {
                //对于包含流的流的展平操作符演示
                (1..3).asFlow().flatMapConcat { value -> requestFlow(value) }
                    .collect { value ->//串行的发射每一个流，遇到挂起直接等待，挂起结束发送接下来的流
                        println(value)
                    }
            }
            launch(Dispatchers.IO) {
                (4..6).asFlow().flatMapMerge { value -> requestFlow(value) }//会并发的收集发射流，快速发射出去
                    .collect { value -> println(value) }
            }
            launch(Dispatchers.IO) {
                (7..9).asFlow().flatMapLatest { value -> requestFlow(value) }//只会发射最近的流
                    .collect { value -> println(value) }
            }
        }
    }

    private fun requestFlow(value: Int): Flow<String> = flow {
        emit("response1 $value")
        delay(100)
        emit("response2 $value")
    }

    /**
     * 加速发射流的操作符*/
    fun test7() {
        CoroutineScope(Dispatchers.Main).launch {
            //buffer
            launch(Dispatchers.IO) {
                println()
                val startTime = System.currentTimeMillis()
                sample7().buffer().collect { value ->
                    print("$value,")
                    delay(300)
                }
                println()
                println("buffer 操作符处理流耗时：${System.currentTimeMillis() - startTime}")
            }
            //conflate
            launch(Dispatchers.IO) {
                println()
                val startTime = System.currentTimeMillis()
                sample7().conflate().collect { value ->
                    print("$value,")
                    delay(300)
                    println()
                    println("conflate done")
                }
                println()
                println("conflate 操作符处理流耗时：${System.currentTimeMillis() - startTime}")//600-700
            }
            //collectLatest
            launch(Dispatchers.IO) {
                println()
                val startTime = System.currentTimeMillis()
                sample7().collectLatest { value ->
                    print("$value,")
                    delay(300)
                    println()
                    println("collectLatest done")
                }
                println()
                println("collectLatest 操作符处理流耗时：${System.currentTimeMillis() - startTime}")//600-700
            }
        }
    }

    private fun sample7(): Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    /**FLOW构建器中代码运行在流的收集器提供的上下文中，如果collect是在IO线程执行的，那么sample5方法也是在IO线程运行的*/
    fun test6() {
        CoroutineScope(Dispatchers.Main).launch {
            launch(Dispatchers.IO) {
                println("first coroutine，thread name：${Thread.currentThread().name}")
                sample6().collect { value -> print("$value,") }
                println()
            }
            delay(100)
            launch(Dispatchers.IO) {
                println("second coroutine，thread name：${Thread.currentThread().name}")
                sample6().collect { value -> print("$value,") }
                println()
            }
        }
    }

    /**注意：flow构建器中的代码必须要保证上下文一致，不允许从其它上下文发射，否则会抛出异常*/
    private fun sample5(): Flow<Int> = flow {
        //withContext(Dispatchers.Main) {
        println("start flow ,thread name：${Thread.currentThread().name}")
        for (i in 1..3) {
            emit(i)
        }
        //}
    }

    /**如果想要改变流的发射线程(跟流的收集线程不一样),也是有办法的，就是使用flowOn操作符*/
    private fun sample6(): Flow<Int> = flow {
        println("start flow ,thread name：${Thread.currentThread().name}")
        for (i in 1..3) {
            emit(i)
        }
    }.flowOn(Dispatchers.Main)


    /**
     * 流操作符
     * 流的操作符都是非挂起函数，但是却可以在操作符中调用挂起函数
     * */
    fun test5() {
        CoroutineScope(Dispatchers.Main).launch {
            //map 操作符
            println("map")
            (1..3).asFlow().map { request -> performRequest(request) }
                .collect { response -> print("$response,") }
            println()
            //filter操作符
            println("filter")
            (1..10).asFlow().filter { request -> request % 2 == 0 }
                .collect { value -> print("$value,") }
            println()
            //transform 转换操作符
            println("transform")
            (1..3).asFlow().transform { request ->
                emit("Making $request")
                emit(performRequest(request))
            }.collect { value -> print("$value,") }
            println()
            //take 限长操作符
            println("take")
            sample4().take(2).collect { value -> print("$value,") }
            println()
            //末端操作符，启动流收集的挂起函数（末端函数都是挂起函数）
            println("collect")
            (1..4).asFlow().collect { value -> print("$value,") }
            println()
            println("tolist")
            val list1: List<Int> = (1..4).asFlow().toList()
            println(list1)
            println("toset")
            val set1: Set<Int> = (1..4).asFlow().toSet()
            println(set1)
            println("first")
            val result1 = (1..4).asFlow().first()
            println("result1：$result1")
            println("single")
            val result2 = (1..4).asFlow().singleOrNull()
            println("result2：$result2")
            println("reduce add")
            val result3 = (1..4).asFlow().reduce { total, next ->//reduce 操作符在介绍list的时候出现过
                print("[$total,$next],")
                total + next
            }
            println()
            println("result3：$result3")
            println("reduce multi")
            val result4 = (1..4).asFlow().reduce { acc, next ->//reduce跟fold功能类似只是没有初始值.将流中的内容累加
                print("[$acc,$next],")
                acc * next
            }
            println()
            println("result4：$result4")
            println("fold add")
            val result5 =
                (1..4).asFlow().fold(10) { total, next ->//fold 操作符在介绍list的时候出现过。将初始值和流中的值累加
                    print("[$total,$next],")
                    total + next
                }
            println()
            println("result5：$result5")
            //小结：reduce、fold不仅仅可以将流或集合中的值累加、累乘，只要对流中相邻元素进行操作，并将返回结果继续跟下一个元素计算的逻辑都可以使用这两种操作符
        }
    }

    /**在该方法中用try-catch包裹代码，是为了验证限长操作符会取消协程的运行*/
    private fun sample4(): Flow<Int> = flow {
        try {
            emit(1)
            emit(2)
            emit(3)
            emit(4)
        } catch (cancel: CancellationException) {
            println("取消流")
        } finally {
            println("清理资源，关闭连接")
        }
    }

    /**定义挂起函数*/
    private suspend fun performRequest(value: Int): String {
        delay(1000)
        return "response $value"
    }

    /**流构建器除了flow还有asFlow，该构建器支持直接集合、数组、序列转换为流*/
    fun test4() {
        CoroutineScope(Dispatchers.Main).launch {
            (1..5).asFlow().collect { value -> println(value) }
            listOf<Int>(1, 2, 3, 4, 5).asFlow().collect { value -> println(value) }
            val array = arrayOfNulls<String>(5)
            array[0] = "a"
            array[1] = "b"
            array[2] = "c"
            array[3] = "d"
            array[4] = "e"
            array.asFlow().collect { value -> println(value) }
        }
    }

    /**流的取消操作*/
    fun test3() {
        CoroutineScope(Dispatchers.Main).launch {
            withTimeoutOrNull(1000) {
                sample3().collect { value ->
                    delay(100)
                    println(value)
                }
            }
        }
    }

    private fun sample3(): Flow<Int> = flow {
        println("start flow")
        for (i in 1..10000) {
//            delay(100)//挂起的流可以取消
            emit(i)
        }
    }

    /**流是冷的：flow构建器中的代码只有在收集的时候才会调用*/
    fun test2() {
        CoroutineScope(Dispatchers.Main).launch {
            println("start call sample2 method")
            val flow = sample2()
            println("start call flow collect")
            flow.collect { value ->//流的collect 方法必须在协程或者挂起函数中调用
                println(value)
            }
            println("start call flow collect again")
            flow.collect { value ->
                println(value)
            }
        }
    }

    private fun sample2(): Flow<Int> = flow {
        println("flow start")
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    fun test1() {
        CoroutineScope(Dispatchers.Main).launch {
            launch {//该协程是在主线程运行的
                for (i in 1..3) {
                    delay(100)
                    println("i am not blocked $i")
                }
            }
            sample().collect { value -> println(value) }
            println("main i am quit")//这行代码为了验证sleep会阻塞主线程
        }
    }

    /**该方法虽然调用了delay方法但是并没有添加suspend*/
    private fun sample(): Flow<Int> = flow {
        for (i in 1..10) {
            delay(100)//该代码不会阻塞主线程
//            Thread.sleep(100)//结果证明(点击其它按钮无反应)，该行代码会阻塞主线程(协程中最后一行代码一直没有打印)
            emit(i)
        }
    }
}