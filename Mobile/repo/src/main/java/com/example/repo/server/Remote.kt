package com.example.repo.server

import android.util.Log
import com.example.data.entity.Cliente
import com.example.data.entity.Empresa
import com.example.data.entity.OS
import com.example.repo.controller.OSCadastro
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

    suspend fun insertOS(os: OSCadastro): Response<ResponseServidor<Boolean>>{
        val json = Gson().toJson(os)
        val body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json)

        val callRetrofitOS = APIAppOSInitializer().apiAppOS().insertOS(body)

        return callRetrofitOS.awaitResponse()
    }

    suspend fun getCliente(busca: String): Response<ResponseServidor<ArrayList<Cliente>>> {
        val callRetrofit = APIAppOSInitializer().apiAppOS().getCliente(busca)
        return callRetrofit.awaitResponse()

    }

    suspend fun getOrdemServico(cpfcnpjEmpresa: String?): Response<ResponseServidor<ArrayList<OS>>> {
        val callRetrofit = APIAppOSInitializer().apiAppOS().getOrdemServico(cpfcnpjEmpresa)
        return  callRetrofit.awaitResponse()
    }

    suspend fun atualizarOS(item: OS): Response<ResponseServidor<Boolean>> {
        val json = Gson().toJson(item)
        val body =
            RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json)
        val callRetrofit = APIAppOSInitializer().apiAppOS().AtualizarOS(body)

        return callRetrofit.awaitResponse()
    }


}