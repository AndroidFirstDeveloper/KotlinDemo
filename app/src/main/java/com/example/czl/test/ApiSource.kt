package com.example.czl.test

import retrofit2.converter.gson.GsonConverterFactory

class ApiSource {

    companion object {
        val retrofitInstance =
            retrofit2.Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(ApiResultCallAdapterFactory())//设置自定义calladapter factory
                .baseUrl("http://219.141.190.225/api/")
                .build()
    }
}