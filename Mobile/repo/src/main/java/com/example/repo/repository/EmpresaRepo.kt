package com.example.repo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.data.dao.EmpresaDao
import com.example.data.database.Database
import com.example.data.entity.Empresa

class EmpresaRepo(application: Application) {
    private val empresaDao: EmpresaDao
    private val empresaLiveData: LiveData<MutableList<Empresa>>

    init {
        val database = Database.getInstance(application)
        empresaDao = database!!.empresaDao()
        empresaLiveData = empresaDao.getAllEmpresas()
    }

    fun getAllEmpresas(): LiveData<MutableList<Empresa>>{
        return empresaLiveData
    }

    fun insert(empresa: Empresa){
        empresaDao.insert(empresa)
    }

    fun delete(empresa: Empresa){
        empresaDao.delete(empresa)
    }

    fun selectEmpresaByCpfCnpj(cpf_cnpj: String): Empresa{
        return empresaDao.selectEmpresaBycpfCnpj(cpf_cnpj)
    }

}