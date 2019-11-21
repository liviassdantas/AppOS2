package com.example.repo.server

import com.example.data.entity.Cliente
import com.example.data.entity.Empresa
import com.example.data.entity.OS
import com.example.repo.util.ResponseServidor
import com.example.repo.util.ResponseViewModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIAppOS {
    @POST("api/EmpresaApiController/Cadastrar")
    fun insertEmpresa(@Body requestBody: RequestBody): Call<ResponseServidor<Boolean>>


    @GET("api/LoginApiController/Entrar")
    fun getLogin(@Query("cpf_cnpj")cpf_cnpj: String,
                 @Query("senha")senha: String):Call<ResponseServidor<Empresa>>

    @POST("api/OrdemServicoApiController/Cadastrar")
    fun insertOS(@Body requestBody: RequestBody): Call<ResponseServidor<Boolean>>

    @GET("api/ClienteApiController/GetCliente")
    fun getCliente(@Query("busca") busca: String): Call<ResponseServidor<ArrayList<Cliente>>>



}


