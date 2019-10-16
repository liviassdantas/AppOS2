package com.example.data.util

import Util.DateTime
import androidx.room.TypeConverter
import com.example.data.entity.Cliente
import com.example.data.entity.Empresa
import com.example.data.entity.Produto
import com.google.gson.Gson
import java.util.*


class Converters {
    @TypeConverter
    fun stringToEmpresa(value: String?): Empresa? {
        return if (value == null) null else Gson().fromJson(value,Empresa::class.java)
    }

    @TypeConverter
    fun EmpresaToString(empresa: Empresa?): String? {
        return if (empresa == null) {
            null
        } else {
            Gson().toJson(empresa)
        }
    }

    @TypeConverter
    fun LongtoDatetime(valor: Long): DateTime?{
        return DateTime(valor)
    }

    @TypeConverter
    fun DatetimeToLong(valor: DateTime): Long? {
        return valor.getData()?.time
    }

    @TypeConverter
    fun stringToProduto(value: String?): Produto? {
        return if (value == null) null else Gson().fromJson(value,Produto::class.java)
    }

    @TypeConverter
    fun ProdutoToString(produto: Produto?): String? {
        return if (produto == null) {
            null
        } else {
            Gson().toJson(produto)
        }
    }

    @TypeConverter
    fun stringToCliente(value: String?): Cliente? {
        return if (value == null) null else Gson().fromJson(value,Cliente::class.java)
    }

    @TypeConverter
    fun ClienteToString(cliente: Cliente?): String? {
        return if (cliente == null) {
            null
        } else {
            Gson().toJson(cliente)
        }
    }
}