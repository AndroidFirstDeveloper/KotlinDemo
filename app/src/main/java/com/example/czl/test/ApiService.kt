package com.example.czl.test

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("configuration/all")
    fun getAndroidGank(): Call<ConfigData>

    @GET("configuration/all")
    fun getIosGank(): Call<ConfigData>

    @GET("configuration/all")
    suspend fun getAndroidGank2(): ConfigData

    @GET("configuration/all")
    suspend fun getAndroidGank3(): Response<ConfigData>

    @GET("configuration/all")
    suspend fun getIosGank2(): ApiResult<ConfigData>
}