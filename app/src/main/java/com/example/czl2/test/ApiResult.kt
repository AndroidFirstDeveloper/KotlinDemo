package com.example.czl2.test

/**自定义接口返回数据*/
public open class ApiResult<out T> {

    data class Success<out T>(val data: T?) : ApiResult<T>()
    data class Failure(val errorCode: Int, val errorMsg: String) : ApiResult<Nothing>()
}