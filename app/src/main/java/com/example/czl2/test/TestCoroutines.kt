package com.example.czl2.test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import kotlinx.coroutines.*
import java.io.InputStream
import java.lang.ArithmeticException
import java.net.HttpURLConnection
import java.net.URL

class TestCoroutines {

    /**测试协程构建器默认的线程*/
    fun test23() {
        CoroutineScope(Dispatchers.Main).launch {
            launch {
                println("thread name：${Thread.currentThread().name}")//main
            }
            async {
                println("thread name：${Thread.currentThread().name}")//main
            }
        }
    }

    /**测试Thread.sleep跟delay的区别*/
    fun test22() {
        CoroutineScope(Dispatchers.Main).launch {
            launch {
                println("start thread name ${Thread.currentThread().name}")
                delay(5000)//从结果来看，delay是不会阻塞父协程中的代码执行的，就好比不会阻塞main线程。同时等待时间结束后，代码继续执行，同时不用显示处理异常(CancelIation)，thread的话必须要加try-catch否则编译报错
                println("continue thread name ${Thread.currentThread().name}")
                println("end thread name ${Thread.currentThread().name}")
            }
            delay(100)//从结果来看，如果没有这行代码的话，子协程中代码执行顺序一般在父协程之后
            println("main start")
            println("main quit")
        }
    }

    private val threadLocal = ThreadLocal<String>()

    /**测试在协程间传值*/
    fun test21() {
        CoroutineScope(Dispatchers.Main).launch {
            threadLocal.set("main")
            println("1-Thread name：${Thread.currentThread().name},thread local value：${threadLocal.get()}")//thread local 的值是main
            launch(Dispatchers.IO + threadLocal.asContextElement("launch")) {
                println("2-Thread name：${Thread.currentThread().name},thread local value：${threadLocal.get()}")//thread local 的值是launch
//                yield()
                delay(1000)
                println("2-Thread name：${Thread.currentThread().name},thread local value：${threadLocal.get()}")//thread local 的值是launch
            }
        }
    }

    class MyActivity {
        //这里定义两个scope为了验证协程的取消跟任务执行的线程是无关的
        private val mainScope = MainScope()
        private val ioScope = CoroutineScope(Dispatchers.IO)

        fun onCreate() {
            println("页面启动")
            repeat(10) { i ->
                mainScope.launch {
                    val time = (i + 1) * 300
                    delay(time.toLong())
                    println("task $i have conduct")
                }
            }
        }

        fun onStart() {
            println("页面可见")
            repeat(1000) { i ->
                ioScope.launch {
                    delay((200 * (i + 1)).toLong())
                    println("iotask $i was conducted")
                }
            }
        }

        fun onDestroy() {
            println("页面关闭")
            mainScope.cancel()//结果证明协程中未执行到的挂起都能被取消
            ioScope.cancel()//结果证明协程中未执行到的挂起都能被取消
        }
    }

    /**协程作用域
    在实际开发中，有时需要根据页面的生命周期做不同的事情，比如页面启动时加载数据，页面关闭时销毁或者停止加载数据，
    这时就需要通过一个协程管理多个任务的生命周期*/
    fun test20() {
        CoroutineScope(Dispatchers.Main).launch {
            val activity = MyActivity()
            activity.onCreate()//启动页面
            activity.onStart()
            delay(1000)//模拟页面正常显示操作部分
            activity.onDestroy()//关闭页面
            delay(1000)
        }
    }

    /**命名协程用于调试
    组合协程上下文中的元素*/
    fun test19() {
        CoroutineScope(Dispatchers.Main).launch {
            launch(CoroutineName("pig")) {
                println("thread name：${Thread.currentThread().name}")
            }
            async(CoroutineName("dog")) {
                println("thread name：${Thread.currentThread().name}")
            }
            launch(Dispatchers.IO + CoroutineName("CAT")) {
                println("thread name：${Thread.currentThread().name}")
            }
        }
    }

    fun test18() {
        CoroutineScope(Dispatchers.Main).launch {
            //当调用 launch { …… } 时不传参数，它从启动了它的 CoroutineScope 中承袭了上下文（以及调度器
            launch {
                println("使用默认launch构建协程，thread name：${Thread.currentThread().name}")//协程的上下文跟外部协程上下文一致
            }
            async {
                println("使用默认async构建协程，thread name：${Thread.currentThread().name}")//协程的上下文跟外部协程上下文一致
            }

            launch(Dispatchers.IO) {
                println("使用指定上下文launch构建协程，thread name：${Thread.currentThread().name}")//协程上下文是IO
            }
            async(Dispatchers.IO) {
                println("使用指定上下文async构建协程，thread name：${Thread.currentThread().name}")//协程上下文是IO
            }
        }
    }

    /**父协程被取消，子协程不一定取消。*/
    fun test17_1() {
        CoroutineScope(Dispatchers.Main).launch {
            val job = CoroutineScope(Dispatchers.Main).launch {
                val job1 = GlobalScope.launch {
                    repeat(1000) {
                        delay(500)
                        println("job1 sleeping 500")//当取消父协程的时候，该协程没有取消，因为该协程的Job跟外部协程Job无关
                    }
                }
                val job2 = launch(Dispatchers.IO) {
                    repeat(1000) {
                        delay(500)
                        println("job2 sleeping 500")//当取消父协程的时候，该协程被取消
                    }
                }
                val job3 = CoroutineScope(Dispatchers.IO).launch {
                    repeat(1000) {
                        delay(500)
                        println("job3 sleeping 500")//当取消父协程的时候，该协程没有取消，因为该协程的job跟外部协程job无关
                    }
                }
            }

            delay(1300)
            println("main i am tired")
            job.cancelAndJoin()
            println("main i am quit")
        }
    }

    /**当父协程被取消，子协程也被取消*/
    fun test17() {
        CoroutineScope(Dispatchers.Main).launch {
            val job1 = CoroutineScope(Dispatchers.Main).launch {
                val job2 = launch(Dispatchers.IO) {
                    try {
                        repeat(1000) {
                            delay(500)
                            println("i am sleeping 500")
                        }
                    } catch (cancel: CancellationException) {
                        println("任务呗取消")
                    } finally {
                        println("释放资源，关闭连接")
                    }
                }
            }
            delay(2000)
            println("main i am tired")
            job1.cancelAndJoin()
            println("main i am quit")
        }
    }

    /**
    运行可取消的代码块
    在取消的协程中调用挂起函数
     */
    fun test16() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val job = launch(Dispatchers.IO) {
                try {
                    repeat(1000) {
                        delay(500)
                        println("i am sleeping 500")
                    }
                } finally {
                    withContext(NonCancellable) {//不取消
                        delay(1000)//挂起函数
                        println("清理资源，关闭连接")//这一行代码能执行
                    }
                }
            }
            delay(1300)
            println("main i am tired")
            job.cancelAndJoin()
            println("main i am quit")
        }
    }

    /**运行不可取消的代码块*/
    fun test15() {
        CoroutineScope(Dispatchers.Main).launch {
            val job = launch(Dispatchers.IO) {
                try {
                    repeat(1000) {
                        delay(500)
                        println("i am sleeping $it")
                    }
                } finally {
                    delay(1000)//调用挂起函数
                    println("清理资源，关闭连接")//这一行代码不会执行，调用挂起函数(finally中的)的时候会检查协程的状态，此时任务已经取消了所以会抛出异常(CancellationException)，所以出于异常之后的代码就不会执行到
                }
            }
            delay(1300)
            println("main i am tired")
            job.cancelAndJoin()
            println("main i am quit")
        }
    }

    /**使用yield方法取消计算的任务*/
    fun test14() {
        CoroutineScope(Dispatchers.Main).launch {
            val job = launch(Dispatchers.IO) {
                var startTime = System.currentTimeMillis()
                var i = 0
                try {
                    while (i < 5) {
                        yield()
                        if (System.currentTimeMillis() > startTime) {
                            println("i am sleeping ${i++}")
                            startTime += 500
                        }
                    }
                } catch (cancel: CancellationException) {
                    println("计算任务被取消")
                } finally {
                    println("清理资源，关闭连接")
                }
            }
            delay(1300)
            println("main i am tired")
            job.cancelAndJoin()
            println("main i am quit")
        }
    }

    /*使用isActive属性取消计算的任务*/
    fun test13() {
        CoroutineScope(Dispatchers.Main).launch {
            val job = launch(Dispatchers.IO) {
                var startTime = System.currentTimeMillis()
                try {
//                    while (isActive) {//方式1
                    while (coroutineContext[Job]?.isActive == true) {//方式2 这两种写法效果是一样的，因为查看isActive的源码就是方式2
                        if (System.currentTimeMillis() > startTime) {
                            println("i am sleeping 500")
                            startTime += 500
                        }
                    }
                } catch (cancel: CancellationException) {
                    println("计算任务呗取消")
                } finally {
                    println("清理资源，关闭连接")
                }
            }
            delay(1300)
            println("main i am tired")
            job.cancelAndJoin()
            println("main i am quit")
        }
    }

    fun test12() {
        CoroutineScope(Dispatchers.Main).launch {
            val job = launch(Dispatchers.IO) {//launch不指定线程的话默认是外部上下文线程
                println("current thread name：${Thread.currentThread().name}")//输出IO线程
                try {
                    repeat(1000) {
                        delay(500)
                        println("i am sleeping $it")
                    }
                } catch (cancel: CancellationException) {
                    println("task is canceled")//当任务被取消会执行
                } finally {
                    println("i am in finally")//任务结束(正常/非正常)的时候会执行,在这里可以进行释放资源，关闭连接操作
                }
            }
            delay(1300)
            println("main i am starting ...")
            job.cancel()//取消协程，立即返回
            job.join()//阻塞当前线程。协程取消了这里还要调用join是因为取消后不一定立即结束，join阻塞等待协程结束再继续执行下面的代码
            println("main i am quit")
        }
    }

    fun test11() {
        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).launch {
                withTimeout(2000) {//超时后下面任务自动停止输出，超时后没有显示的输出异常
                    println("current thread name：${Thread.currentThread().name}")//输出IO线程
                    repeat(1000) {
                        delay(500)
                        println("i am sleeping $it")
                    }
                }
            }
            println("i am main fast")//这行会先输出
        }
    }

    fun test10() {
        CoroutineScope(Dispatchers.Main).launch {
            val result1: Deferred<Int> = async {//带返回结果的异步任务
                repeat(10) {
                    delay(500)
                    println("i am sleeping $it")
                }
                10
            }
            println("main i am start waiting ...")//会立即打印
            println("main i am waiting the result：${result1.await()}")//一直等待async任务执行结束
            println("main i am quit")//async任务执行结束才会执行到这里
        }
    }

    fun test9() {
        CoroutineScope(Dispatchers.Main).launch {
            val job = launch(Dispatchers.IO) {
                repeat(1000) {
                    delay(500)
                    println("I am sleeping $it")
                }
            }
            delay(1300)//挂起一段时间，继续向下执行代码
            println("main I am tired")
            job.cancel()//取消任务
//            job.join()//这一行好像没什么用，因为输出没有变化
            println("main i am quit")//这行代码先执行完毕，任务如果不取消的话，任务会继续输出
        }
    }

    /**使用async的结构化并发测试报错
     * 从打印结果中可知，test8方法中第二个协程未受到异常的影响（前提异常已经被捕获掉）
     * */
    fun test8() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                failedConcurrentSum()
            } catch (e: ArithmeticException) {
                println("取消协程方法执行")
            }
            val result = withContext(Dispatchers.IO) {
                delay(2000)
                100
            }
            println("协程中第二个方法执行结果：$result")
        }
    }

    /**结构化并发。从打印结果来看，结构化并发模块中的一个协程发生异常后，模块中其它的协程都会被取消*/
    private suspend fun failedConcurrentSum() = coroutineScope {
        /**这里第一个协程delay很长时间，就是为了保证在第二个协程执行完之前其还未执行完，从而验证第二个协程中的异常是否会影响其代码执行*/
        val result1 = async {
            try {
                delay(Long.MAX_VALUE)
                10
            } finally {
                println("first child canceled because come across exception")
            }
        }
        val result2: Deferred<Int> = async {
            println("second child happened exception")
            throw ArithmeticException()
        }
        result1.await() + result2.await()
    }

    /**使用async的结构化并发*/
    fun test7() {
        CoroutineScope(Dispatchers.Main).launch {
            println("test7计算结果：${concurrentSum()}")
        }
    }

    private suspend fun concurrentSum() = coroutineScope {
        val result1 = async { compute1() }
        val result2 = async { compute2() }
        result1.await() + result2.await()
    }

    /**async风格的函数*/
    fun test6() {
        val result1 = compute1Async()
        val result2 = compute2Async()
        val startTime = System.currentTimeMillis()
        CoroutineScope(Dispatchers.Main).launch {
            println("${result1.await()}+${result2.await()}")//虽然async风格的函数可以随意在方法中使用，但是返回的Deffered结果需要在协程中使用
            println("test6方法耗时：${System.currentTimeMillis() - startTime}")
        }
    }

    /**这种风格的函数可以在任何地方调用，不需要在协程中使用
    但是这种风格在kotlin中是强烈不推荐的，这里只是用作一种练习、演示*/
    private fun compute1Async() = GlobalScope.async {
        compute1()
    }

    private fun compute2Async() = GlobalScope.async {
        compute2()
    }

    /**惰性启动的async*/
    fun test5() {
        CoroutineScope(Dispatchers.Main).launch {
            println("test5开始执行")
            val startTime = System.currentTimeMillis()
            val result1 = async(start = CoroutineStart.LAZY) { compute1() }
            val result2 = async(start = CoroutineStart.LAZY) { compute2() }
            println("惰性async启动耗时1：${System.currentTimeMillis() - startTime}")
            result1.start()
            result2.start()
            println("惰性async启动耗时2：${System.currentTimeMillis() - startTime}")
            println("test5返回结果：${result1.await() + result2.await()}")
            println("test5执行耗时：${System.currentTimeMillis() - startTime}")
        }
    }

    private suspend fun compute1(): Int {
        delay(1000)
        return 10
    }

    private suspend fun compute2(): Int {
        delay(1000)
        return 20
    }

    fun test4(imageView1: ImageView, imageView2: ImageView) {
        CoroutineScope(Dispatchers.Main).launch {
            var startTime = System.currentTimeMillis()
            println("test4方法开始执行")
            val image = withContext(Dispatchers.IO) {
                getImage()
            }
            println("test4加载图片耗时：${System.currentTimeMillis() - startTime}")
            startTime = System.currentTimeMillis()
            val result1: Deferred<Bitmap> = async { getSlipImage1(image) }
            val result2: Deferred<Bitmap> = async { getSlipImage2(image) }
            println("test4两个方法执行结束耗时：${System.currentTimeMillis() - startTime}")
            imageView1.setImageBitmap(result1.await())
            imageView2.setImageBitmap(result2.await())
            println("test4截取图片耗时：${System.currentTimeMillis() - startTime}")
        }
    }

    private suspend fun getSlipImage1(image: Bitmap): Bitmap {
//        getSlipImage1方法中如果没有调用delay方法是不用添加suspend关键字的
        //这里添加delay方法是因为裁剪图片的计算其实耗时很小-11毫秒，无法体现async中执行耗时操作的具体表现
        delay(1000)
        val targetWidth = image.width / 2
        val targetHeight = image.height / 2
        return Bitmap.createBitmap(image, 0, 0, targetWidth, targetHeight, null, false)
    }

    private suspend fun getSlipImage2(image: Bitmap): Bitmap {
        delay(1000)
        val targetWidth = image.width / 3
        val targetHeight = image.height / 3
        return Bitmap.createBitmap(
            image,
            targetWidth * 2,
            targetHeight * 2,
            targetWidth,
            targetHeight,
            null,
            false
        )
    }

    fun test3() {
        CoroutineScope(Dispatchers.Main).launch {
            val startTime = System.currentTimeMillis()
            println("开始执行test3")
            val result1: Deferred<Int> = async { getWeight1() }
            val result2: Deferred<Int> = async { getWeight2() }
            println("test3两个方法执行结束，耗时：${System.currentTimeMillis() - startTime}")
            println("${result1.await()}+${result2.await()}=${result1.await() + result2.await()}")
            //从打印结果来看，test3中的两个方法的执行顺序是并行执行的
            println("结束执行test3，耗时：${System.currentTimeMillis() - startTime}")
        }
    }

    private suspend fun getWeight1(): Int {
        delay(1000)
        return 11
    }

    private suspend fun getWeight2(): Int {
        delay(1000)
        return 21
    }


    /**协程的代码块中，线程执行到了 suspend 函数这里的时候，就暂时不再执行剩余的协程代码，跳出协程的代码块。
    那线程接下来会做什么呢？
    如果它是一个后台线程：
    要么无事可做，被系统回收
    要么继续执行别的后台任务
    跟 Java 线程池里的线程在工作结束之后是完全一样的：回收或者再利用。
    如果这个线程它是 Android 的主线程，那它接下来就会继续回去工作：也就是一秒钟 60 次的界面刷新任务*/
    fun test2() {
        CoroutineScope(Dispatchers.Main).launch {
            val startTime = System.currentTimeMillis()
            println("开始执行test2")
            val result1 = getPrice1()
            val result2 = getPrice2()
            //从打印结果来看，两个方法的执行顺序是串行执行
            println("结束执行test2，耗时：${System.currentTimeMillis() - startTime}")
            println("$result1+$result2=${result1 + result2}")
        }
    }

    private suspend fun getPrice1() = withContext(Dispatchers.IO) {
        delay(1000)
        10
    }

    private suspend fun getPrice2() = withContext(Dispatchers.IO) {
        delay(1000)
        20
    }

    fun test1(imageView: ImageView) {
        CoroutineScope(Dispatchers.Main).launch {
            println("coroutines start")
            println("thread name1：${Thread.currentThread().name}")//打印当前协程的线程名(配置虚拟机参数：-Dkotlinx.coroutines.debug后还会打印协程的名字)
            //下载图片
            val image = withContext<Bitmap>(Dispatchers.IO) {
                println("thread name2：${Thread.currentThread().name}")
                getImage()
            }
            println("coroutines end")
            imageView.setImageBitmap(image)//设置图片
        }
    }

    private fun getImage(): Bitmap {
        val urlParams =
            URL("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic34.photophoto.cn%2F20150122%2F0005018375379648_b.jpg&refer=http%3A%2F%2Fpic34.photophoto.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1616924761&t=3d4835aaef07dab00c5969648e52e0fb")
        val openConnection = urlParams.openConnection() as HttpURLConnection
        openConnection.requestMethod = "GET"
        openConnection.connect()
        val inputStream: InputStream = openConnection.inputStream
        return BitmapFactory.decodeStream(inputStream)
    }
}