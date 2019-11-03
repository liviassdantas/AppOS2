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
    var retorno = MutableLiveData<ResponseViewModel<Any>>()
    val empresaLiveData: LiveData<ResponseViewModel<Any>> = retorno

    fun insertServidor(request: Int, empresa: Empresa) = GlobalScope.launch(Dispatchers.Main) {

        val ret = EmpresaRepo(app).insertServidor(empresa)
        val dispo = ResponseViewModel<Any>()
        dispo.id = request
        dispo.exception = ret.body()?.exception?.let { Exception(it)}
        dispo.mensagem = ret.body()?.mensagem
        dispo.objeto = ret.body()?.objeto

        retorno.postValue(dispo)

    }

    fun getLogin (request: Int, cpf_cnpj: String, senha: String) = GlobalScope.launch(Dispatchers.Main) {
        val ret = EmpresaRepo(app).getLogin(cpf_cnpj, senha)
        val dispo = ResponseViewModel<Any>()
        dispo.id = request
        dispo.exception = ret.body()?.exception?.let { Exception(it)}
        dispo.mensagem = ret.body()?.mensagem
        dispo.objeto = ret.body()?.objeto

        retorno.postValue(dispo)

    }



}