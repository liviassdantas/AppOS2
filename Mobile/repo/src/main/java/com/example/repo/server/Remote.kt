package com.example.repo.server

import android.util.Log
import com.example.data.entity.Cliente
import com.example.data.entity.Empresa
import com.example.data.entity.OS
import com.example.repo.util.ResponseServidor
import com.google.gson.Gson
import okhttp3.RequestBody
import retrofit2.*

class Remote {

    suspend fun insertServidor(empresa: Empresa): Response<ResponseServidor<Boolean>> {
        val json = Gson().toJson(empresa)
        val body =
            RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json)

        val callRetrofitEmpresa = APIAppOSInitializer().apiAppOS()
            .insertEmpresa(body)

        return callRetrofitEmpresa.awaitResponse()
    }

    suspend fun getLoginEmpresa(
        cpf_cnpj: String,
        senha: String
    ): Response<ResponseServidor<Empresa>> {
        val callRetrofit = APIAppOSInitializer().apiAppOS().getLogin(cpf_cnpj, senha)

        return callRetrofit.awaitResponse()
    }

    suspend fun insertOS(os: OS): Response<ResponseServidor<Boolean>>{
        val json = Gson().toJson(os)
        val body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json)

        val callRetrofitOS = APIAppOSInitializer().apiAppOS().insertOS(body)

        return callRetrofitOS.awaitResponse()
    }

    suspend fun getCliente(cpfCnpj: String): Response<ResponseServidor<Cliente>> {
        val callRetrofit = APIAppOSInitializer().apiAppOS().getCliente(cpfCnpj)
        return callRetrofit.awaitResponse()

    }
//    suspend fun getOrdemServico(
//        num_os: String,
//        cpf_cnpj: String
//    ): Response<ResponseServidor<Empresa>> {
//        val callRetrofit = APIAppOSInitializer().apiAppOS().getOrdemServico(num_os, cpf_cnpj)
//
//        return callRetrofit.awaitResponse()
//    }

}