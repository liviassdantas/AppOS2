package com.example.repo.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.data.dao.OSDao
import com.example.data.database.Database
import com.example.data.entity.Cliente
import com.example.data.entity.OS
import com.example.repo.controller.OSCadastro
import com.example.repo.server.Remote
import com.example.repo.util.ResponseServidor
import retrofit2.Response

class OSRepo(private val context: Context) {

    suspend fun insertServidorOS(os: OSCadastro): Response<ResponseServidor<Boolean>> {
        return Remote().insertOS(os)
    }

    suspend fun GetCliente(cpfCnpj: String): Response<ResponseServidor<ArrayList<Cliente>>>{
        return Remote().getCliente(cpfCnpj)
    }
}