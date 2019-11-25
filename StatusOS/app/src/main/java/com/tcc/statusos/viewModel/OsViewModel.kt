package com.tcc.statusos.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.repo.util.ResponseViewModel
import com.tcc.repo.repo.OSRepo
import com.tcc.statusos.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class OsViewModel (private val app:Application) :AndroidViewModel(app){
    val retorno = MutableLiveData<ResponseViewModel<Any>>()
    val osLiveData: LiveData<ResponseViewModel<Any>> = retorno

    fun getOrdemServico(request :Int, numOS:String , cpfCliente:String) = GlobalScope.launch (Dispatchers.IO){
        val dispo = ResponseViewModel<Any>()
        dispo.id = request
        try{
            val ret = OSRepo(app).getOrdemServico(numOS,cpfCliente)
            dispo.exception = ret.body()?.exception?.let { Exception(it) }
            dispo.mensagem = ret.body()?.mensagem
            dispo.objeto = ret.body()?.objeto
        }catch (ex:Exception){
            dispo.exception = Exception(app.getString(R.string.sem_conexao))
        }

        retorno.postValue(dispo)

    }

}