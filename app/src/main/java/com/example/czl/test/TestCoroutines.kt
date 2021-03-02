package com.example.czl.test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import kotlinx.coroutines.*
import java.io.InputStream
import java.lang.ArithmeticException
import java.net.HttpURLConnection
import java.net.URL

class TestCoroutines {

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