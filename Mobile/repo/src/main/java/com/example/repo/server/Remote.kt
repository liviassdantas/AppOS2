package com.example.repo.server

import android.content.Context
import android.util.Log
import com.example.data.entity.Empresa
import com.example.repo.util.ResponseServidor
import com.example.repo.util.ResponseViewModel
import com.google.gson.Gson
import connectivity.Server
import okhttp3.RequestBody
import retrofit2.*

class Remote{

     suspend fun insertServidor(empresa: Empresa): Response<ResponseServidor<Boolean>> {
        val json = Gson().toJson(empresa)
        val body =
            RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json)

        val callRetrofitEmpresa = EmpresaInitializer().apiCadastrarEmpresa()
            .insertEmpresa(body)


        callRetrofitEmpresa.clone().enqueue(object : Callback<ResponseServidor<Boolean>> {
            override fun onFailure(call: Call<ResponseServidor<Boolean>>, t: Throwable) {
                Log.e("Remote", t.message!!)

            }

            override fun onResponse(
                call: Call<ResponseServidor<Boolean>>,
                response: Response<ResponseServidor<Boolean>>
            ) {
                Log.i("Remote", response.code().toString()+" - " + response.message())
            }

        })
        return callRetrofitEmpresa.awaitResponse()
    }

}