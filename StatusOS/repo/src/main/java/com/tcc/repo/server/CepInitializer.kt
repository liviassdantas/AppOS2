package com.example.repo.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CepInitializer {
    fun init(){}

    val retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun apiCep(): APICep {
        return retrofit.create(APICep::class.java)
    }

}