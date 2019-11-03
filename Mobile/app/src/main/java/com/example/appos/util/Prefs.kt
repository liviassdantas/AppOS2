package com.example.appos.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.data.entity.Empresa
import com.google.gson.Gson

class Prefs(val context: Context) {
    private val PREFS_NAME = "apposprefs"
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun setUsuario(empresa: Empresa){
        sharedPreferences.edit(true){
            putString("Empresa", Gson().toJson(empresa))
        }
    }

    fun getUsuario():Empresa?{
        val empresa = sharedPreferences.getString("Empresa","")
        return if(empresa.isNullOrEmpty()) null else Gson().fromJson(empresa,Empresa::class.java)
    }

}