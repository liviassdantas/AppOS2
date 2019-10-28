package com.example.appos.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.entity.Empresa
import com.example.repo.repository.EmpresaRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class EmpresaView(private val app: Application) : AndroidViewModel(app) {
    val empresaLiveData = MutableLiveData<MutableList<Empresa>>()
    val ret  = MutableLiveData<Long>()
    val retorno = MutableLiveData<Boolean>()

    fun getAll() = GlobalScope.launch(Dispatchers.IO) {
        val retorno = EmpresaRepo(app).getAllEmpresas()
        empresaLiveData.postValue(retorno.value)
    }

    fun insert(empresa: Empresa) = GlobalScope.launch(Dispatchers.IO) {
        val id = EmpresaRepo(app).insert(empresa)
        ret.postValue(id)
    }

    fun insertServidor(empresa: Empresa)= GlobalScope.launch(Dispatchers.Main){
        val empresaPost = EmpresaRepo(app).insertServidor(empresa)
        retorno.postValue(empresaPost)
    }

    fun delete(empresa: Empresa) = GlobalScope.launch(Dispatchers.IO) {
        EmpresaRepo(app).delete(empresa)
    }

    fun getByCpfCnpj(cpf_cnpj: String) = GlobalScope.launch(Dispatchers.IO) {
            val retorno = EmpresaRepo(app).selectEmpresaByCpfCnpj(cpf_cnpj)
            empresaLiveData.postValue(mutableListOf(retorno))
        }



}