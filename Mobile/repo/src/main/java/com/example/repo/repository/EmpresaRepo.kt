package com.example.repo.repository

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.data.dao.EmpresaDao
import com.example.data.database.Database
import com.example.data.entity.Empresa
import com.example.repo.R
import com.example.repo.server.APIEmpresa
import com.example.repo.server.EmpresaInitializer
import com.example.repo.server.Remote
import com.example.repo.util.ResponseServidor
import com.example.repo.util.ResponseViewModel
import com.google.gson.Gson
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import java.lang.Exception

class EmpresaRepo(private val context: Context) {
    /*private val empresaDao: EmpresaDao
    private val empresaLiveData: LiveData<MutableList<Empresa>>

    init {
        val database = Database.getInstance(application)
        empresaDao = database!!.empresaDao()
        empresaLiveData = empresaDao.getAllEmpresas()
    }

    fun getAllEmpresas(): LiveData<MutableList<Empresa>> {
        return empresaLiveData
    }

    fun insert(empresa: Empresa): Long {
        return empresaDao.insert(empresa)
    }*/

    suspend fun insertServidor(empresa: Empresa): Response<ResponseServidor<Boolean>>{
       return Remote().insertServidor(empresa)

    }

    /* fun delete(empresa: Empresa) {
         empresaDao.delete(empresa)
     }

     fun selectEmpresaByCpfCnpj(cpf_cnpj: String): Empresa {
         return empresaDao.selectEmpresaBycpfCnpj(cpf_cnpj)
     }*/

}