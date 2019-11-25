package com.example.repo.server

import android.util.Log

import com.example.repo.util.ResponseServidor
import com.google.gson.Gson
import com.tcc.data.entity.OrdemServico
import okhttp3.RequestBody
import retrofit2.*

class Remote {
    suspend fun getOrdem(num: String, cpfCliente: String): Response<ResponseServidor<OrdemServico>> {
        val callRetrofit = APIAppOSInitializer().apiAppOS().getOrdemServico(num,cpfCliente)
        return  callRetrofit.awaitResponse()
    }



}