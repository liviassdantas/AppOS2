package com.example.appos.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appos.R
import com.example.data.entity.Empresa
import com.example.repo.repository.EmpresaRepo
import com.example.repo.util.ResponseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.Exception


class EmpresaView(private val app: Application) : AndroidViewModel(app) {
    var retorno = MutableLiveData<ResponseViewModel<Any>>()
    val empresaLiveData: LiveData<ResponseViewModel<Any>> = retorno

    fun insertServidor(request: Int, empresa: Empresa) = GlobalScope.launch(Dispatchers.Main) {
        val dispo = ResponseViewModel<Any>()
        dispo.id = request

        try {
            val ret = EmpresaRepo(app).insertServidor(empresa)
            dispo.exception = ret.body()?.exception?.let { Exception(it) }
            dispo.mensagem = ret.body()?.mensagem
            dispo.objeto = ret.body()?.objeto
        } catch (ex: Exception) {
            dispo.exception = Exception(app.getString(R.string.sem_conexao))
        }
        retorno.postValue(dispo)

    }

    fun getLogin(request: Int, cpf_cnpj: String, senha: String) =
        GlobalScope.launch(Dispatchers.Main) {
            val dispo = ResponseViewModel<Any>()
            dispo.id = request
            try {
                val ret = EmpresaRepo(app).getLogin(cpf_cnpj, senha)
                dispo.exception = ret.body()?.exception?.let { Exception(it) }
                dispo.mensagem = ret.body()?.mensagem
                dispo.objeto = ret.body()?.objeto

            } catch (ex: Exception) {
                dispo.exception = Exception(app.getString(R.string.sem_conexao))
            }


            retorno.postValue(dispo)

        }

    fun limparDados() {
        retorno.postValue(ResponseViewModel())

    }


}