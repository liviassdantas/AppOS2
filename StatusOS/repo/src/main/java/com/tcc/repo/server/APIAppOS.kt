package com.example.repo.server


import com.example.repo.util.ResponseServidor
import com.example.repo.util.ResponseViewModel
import com.tcc.data.entity.OrdemServico
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIAppOS {
    @GET("api/OrdemServicoApiController/GetOrdemServico")
    fun getOrdemServico(@Query("Num_OS")num: String, @Query("CPFCNPJ")cpfCliente: String): Call<ResponseServidor<OrdemServico>>






}


