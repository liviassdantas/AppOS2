package com.example.repo.server

import com.example.data.entity.Empresa
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIEmpresa {
    @POST("value")
    fun insertEmpresa(@Body empresa: Empresa): Call<Empresa>

}
