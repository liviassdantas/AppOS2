package com.example.repo.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIAppOSInitializer {

    fun init(){}

    val empresaRetro: Retrofit? = Retrofit.Builder()
        .baseUrl("http://192.168.0.25/AppOS/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun apiAppOS(): APIAppOS {
        return empresaRetro?.create(APIAppOS::class.java)!!
    }




}