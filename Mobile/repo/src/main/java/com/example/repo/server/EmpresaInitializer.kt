package com.example.repo.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EmpresaInitializer {

    fun init(){}

    val empresaRetro = Retrofit.Builder()
        .baseUrl("http://192.168.0.7/appos/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun apiCadastrarEmpresa(): APIEmpresa {
        return empresaRetro.create(APIEmpresa::class.java)
    }



}