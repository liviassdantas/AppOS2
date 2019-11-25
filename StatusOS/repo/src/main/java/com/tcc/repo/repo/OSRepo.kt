package com.tcc.repo.repo

import android.content.Context
import com.example.repo.server.Remote
import com.example.repo.util.ResponseServidor
import com.tcc.data.entity.OrdemServico
import retrofit2.Response

class OSRepo(private val context: Context) {

    suspend fun getOrdemServico(
        num: String,
        cpfCliente: String
    ): Response<ResponseServidor<OrdemServico>> {
        return Remote().getOrdem(num, cpfCliente)
    }
}