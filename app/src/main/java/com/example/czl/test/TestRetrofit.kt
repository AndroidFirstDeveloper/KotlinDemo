package com.example.czl.test

import android.util.Log
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.math.log

class TestRetrofit {

    private suspend fun <T> Call<T>.wait(): T {
        return suspendCoroutine {
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        it.resume(response.body()!!)
                    } else {
                        it.resumeWithException(Throwable(response.toString()))
                    }
                }
            })
        }
    }

    fun test1() {
        CoroutineScope(Dispatchers.Main).launch {
            val startTime = System.currentTimeMillis()
            val callAndroid: Call<ConfigData> =
                ApiSource.retrofitInstance.create(ApiService::class.java).getAndroidGank()
            val callIos: Call<ConfigData> =
                ApiSource.retrofitInstance.create(ApiService::class.java).getIosGank()
            println("请求android开始")
            val result1 = callAndroid.wait()
            println("请求ios开始")
            val result2 = callIos.wait()
            println("请求结束")
            println("result1：${result1.code}")
            println("result2：${result2.code}")
            println("program quit，耗时：${System.currentTimeMillis() - startTime}")
        }
    }

    fun test2() {
        CoroutineScope(Dispatchers.Main).launch {
            val startTime = System.currentTimeMillis()
            val androidDeffered =
                async {
                    ApiSource.retrofitInstance.create(ApiService::class.java).getAndroidGank()
                        .wait()
                }
            val iosDeffered =
                async {
                    ApiSource.retrofitInstance.create(ApiService::class.java).getIosGank().wait()
                }
            println("开始加载android")
            val result1 = androidDeffered.await()//await方法只是等待请求的结果，并不是在此刻才开始请求，请求在async创建的时候就开始了
            println("开始加载ios")
            val result2 = iosDeffered.await()
            println("result1:${result1.code}")
            println("result2:${result2.code}")
            println("program quit：${System.currentTimeMillis() - startTime}")
        }
    }


    fun test3() {
        CoroutineScope(Dispatchers.Main).launch {
            val result1 =
                ApiSource.retrofitInstance.create(ApiService::class.java)
                    .getAndroidGank2()//retrofit2.6.0支持挂起函数，直接返回结果
            println("result:${result1.code}")
        }
    }

    fun test4() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response1 =
                    ApiSource.retrofitInstance.create(ApiService::class.java)
                        .getAndroidGank3()//retrofit2.6.0支持挂起函数，直接返回结果
                if (response1.isSuccessful) {
                    println("请求成功")
                    if (response1.body() != null) {
                        println("version：${response1.body()?.result?.version}")
                    } else {
                        println("请求返回空数据")
                    }
                } else {
                    println("请求报错")
                }
            } catch (e: Throwable) {//这里加上异常捕获是因为请求过程可能会出现异常(不是response失败)
                print("Caught:$e")
            } finally {
                println("清理资源，关闭连接...")
            }
        }
    }

    /**测试自定义结果处理器：CallAdapter*/
    fun test5() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val apiResult: ApiResult<ConfigData> =
                    ApiSource.retrofitInstance.create(ApiService::class.java).getIosGank2()
                when (apiResult) {
                    is ApiResult.Success<ConfigData> -> {
                        Log.e("TestRetrofit", "test5: " + apiResult.data?.code)
                    }
                    is ApiResult.Failure -> {
                        Log.e(
                            "TestRetrofit",
                            "test5: code=" + apiResult.errorCode + " msg:" + apiResult.errorMsg
                        )
                    }
                }
                Log.e("TestRetrofit", "test5: 11111")
            } catch (e: Throwable) {
                Log.e("TestRetrofit", "test5: ", e)
            }
        }
    }
}