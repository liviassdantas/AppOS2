package com.example.repo.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIAppOSInitializer {

    fun init(){}

    val empresaRetro: Retrofit? = Retrofit.Builder()
        .baseUrl("http://ec2-18-231-54-143.sa-east-1.compute.amazonaws.com/APPOS/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun apiAppOS(): APIAppOS {
        return empresaRetro?.create(APIAppOS::class.java)!!
    }




}