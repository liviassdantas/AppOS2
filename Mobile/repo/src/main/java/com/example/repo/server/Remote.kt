package com.example.repo.server

import android.content.Context
import com.example.data.entity.Empresa
import connectivity.Server

abstract class Remote(val context: Context) : List<Empresa> {
    fun teste(v:String){
        val server =  Server<Empresa>(context,"http://192.168.0.53")

        val retorno  = server.GET(Empresa::class.java, AppOSEnum.exemplo, "cnpj=${v}")

    }

}