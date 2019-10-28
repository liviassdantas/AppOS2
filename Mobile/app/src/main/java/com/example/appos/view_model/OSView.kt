package com.example.appos.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.data.entity.OS
import com.example.repo.repository.OSRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OSView(private val app: Application) : AndroidViewModel(app) {
    val osLiveData = MutableLiveData<MutableList<OS>>()
    val ret  = MutableLiveData<Long>()

    fun getAllOrdemServico(empresa: String) = GlobalScope.launch(Dispatchers.IO) {
        val retorno = OSRepo(app).getAllOrdemServico(empresa)
        osLiveData.postValue(retorno.value)
    }


}