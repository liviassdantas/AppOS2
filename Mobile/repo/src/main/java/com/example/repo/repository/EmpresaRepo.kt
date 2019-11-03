package com.example.repo.repository

import android.content.Context
import com.example.data.entity.Empresa
import com.example.repo.server.Remote
import com.example.repo.util.ResponseServidor
import retrofit2.Response

class EmpresaRepo(private val context: Context) {

    suspend fun insertServidor(empresa: Empresa): Response<ResponseServidor<Boolean>>{
       return Remote().insertServidor(empresa)

    }

    suspend fun getLogin(cpf_cnpj:String, senha:String):Response<ResponseServidor<Empresa>>{
        return Remote().getLoginEmpresa(cpf_cnpj,senha)
    }


}