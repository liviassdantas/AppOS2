package com.example.repo.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.data.dao.EmpresaDao
import com.example.data.database.Database
import com.example.data.entity.Empresa
import com.example.repo.server.APIEmpresa
import com.example.repo.server.EmpresaInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmpresaRepo(application: Application) {
    private val empresaDao: EmpresaDao
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
    }

    fun insertServidor(empresa: Empresa): Boolean {
        val callRetrofitEmpresa = EmpresaInitializer().apiCadastrarEmpresa()
            .insertEmpresa(empresa)
        callRetrofitEmpresa.enqueue(object : Callback<Empresa> {
            override fun onFailure(call: Call<Empresa>, t: Throwable) {
                Log.e("RetroError", t.message!!)
            }
            override fun onResponse(call: Call<Empresa>, response: Response<Empresa>) {
                response.let {
                    val empresaResp: Empresa? = it.body()
                    if (response.isSuccessful && empresaResp != null){
                     Log.i("RetroSuccess", "A Empresa ${empresaResp.nome} foi gravada com sucesso")
                    }else{
                     Log.e("RetroError", response.message())
                    }
                }

            }
        })

        return true
    }

    fun delete(empresa: Empresa) {
        empresaDao.delete(empresa)
    }

    fun selectEmpresaByCpfCnpj(cpf_cnpj: String): Empresa {
        return empresaDao.selectEmpresaBycpfCnpj(cpf_cnpj)
    }

}