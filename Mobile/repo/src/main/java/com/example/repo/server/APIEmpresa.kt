package com.example.repo.server

import com.example.data.entity.Empresa
import com.example.repo.util.ResponseServidor
import com.example.repo.util.ResponseViewModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIEmpresa {
    @POST("api/EmpresaApiController/Cadastrar")
    fun insertEmpresa(@Body requestBody: RequestBody): Call<ResponseServidor<Boolean>>

}
