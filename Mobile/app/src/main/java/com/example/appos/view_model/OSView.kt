package com.example.appos.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.entity.OS
import com.example.repo.repository.OSRepo
import com.example.repo.util.ResponseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OSView(private val app: Application) : AndroidViewModel(app) {
    var retorno = MutableLiveData<ResponseViewModel<Any>>()
    val osLiveData: LiveData<ResponseViewModel<Any>> = retorno

    fun insertOS(request: Int, os: OS) = GlobalScope.launch(Dispatchers.Main) {

        val ret = OSRepo(app).insertServidorOS(os)
        val dispo = ResponseViewModel<Any>()
        dispo.id = request
        dispo.exception = ret.body()?.exception?.let { Exception(it)}
        dispo.mensagem = ret.body()?.mensagem
        dispo.objeto = ret.body()?.objeto

        retorno.postValue(dispo)

    }


    fun getCliente(request: Int, cpf_cnpj:String) = GlobalScope.launch(Dispatchers.Main) {

    }





}