package com.example.czl.test

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**自定义Adapter*/
class ApiResultCallAdapter<T>(val dataType: Type) : CallAdapter<T, Call<ApiResult<T>>> {

    override fun responseType(): Type {
        return dataType
    }

    override fun adapt(call: Call<T>): Call<ApiResult<T>> {
        return ApiResultCall(call)
    }

}