package com.example.appos.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.entity.Empresa
import com.example.repo.repository.EmpresaRepo
import com.example.repo.util.ResponseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception


class EmpresaView(private val app: Application) : AndroidViewModel(app) {
    private val retorno = MutableLiveData<ResponseViewModel<Any>>()
    val empresaLiveData: LiveData<ResponseViewModel<Any>> = retorno
//    val ret  = MutableLiveData<Long>()
//    val retorno = MutableLiveData<Boolean>()

    /*fun getAll() = GlobalScope.launch(Dispatchers.IO) {
        val retorno = EmpresaRepo(app).getAllEmpresas()
        empresaLiveData.postValue(retorno.value)
    }

    fun insert(empresa: Empresa) = GlobalScope.launch(Dispatchers.IO) {
        val id = EmpresaRepo(app).insert(empresa)
        ret.postValue(id)
    }*/

    fun insertServidor(request: Int, empresa: Empresa) = GlobalScope.launch(Dispatchers.Main) {

        val ret = EmpresaRepo(app).insertServidor(empresa)
        val dispo = ResponseViewModel<Any>()
        dispo.id = request
        dispo.exception = ret.body()?.exception
        dispo.mensagem = ret.body()?.mensagem
        dispo.objeto = ret.body()?.objeto

        retorno.postValue(dispo)

    }

    /*fun delete(empresa: Empresa) = GlobalScope.launch(Dispatchers.IO) {
        EmpresaRepo(app).delete(empresa)
    }

    fun getByCpfCnpj(cpf_cnpj: String) = GlobalScope.launch(Dispatchers.IO) {
            val retorno = EmpresaRepo(app).selectEmpresaByCpfCnpj(cpf_cnpj)
            empresaLiveData.postValue(mutableListOf(retorno))
    }*/


}