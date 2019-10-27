package com.example.data.util

import Util.DateTime
import androidx.room.TypeConverter
import com.example.data.entity.Cliente
import com.example.data.entity.Empresa
import com.example.data.entity.Produto
import com.example.data.entity.StatusEnum
import com.google.gson.Gson
import java.util.*


class Converter {
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

    @TypeConverter
    fun enumToInt(enum: StatusEnum?): Int?{
        return enum?.value
    }

    @TypeConverter
    fun intToEnum(num: Int?): StatusEnum?{
        return if(num == null) null else {
            when(num){
                0 -> StatusEnum.Aguardando_Inicio
                1 -> StatusEnum.Aguardando_Materiais
                2 -> StatusEnum.Em_Desenvolvimento
                3 -> StatusEnum.Aguardando_Liberacao
                4 -> StatusEnum.Retirada_Disponivel
                else ->null
            }
        }
    }
}