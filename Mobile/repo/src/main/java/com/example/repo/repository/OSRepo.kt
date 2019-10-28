package com.example.repo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.data.dao.OSDao
import com.example.data.database.Database
import com.example.data.entity.OS

class OSRepo(application: Application) {
    private val osDao: OSDao
    private val osLiveData: LiveData<MutableList<OS>>

    init {
        val database = Database.getInstance(application)
        osDao = database!!.osDao()
        osLiveData = osDao.getAll() //TODO trocar para a chamada do servidor depois
    }

    fun getAllOrdemServico(empresa: String): LiveData<MutableList<OS>>{
        return osDao.getAllOrdemServico(empresa)
    }
}