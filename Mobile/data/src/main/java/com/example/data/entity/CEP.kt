package com.example.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CEP")
data class CEP(
    @PrimaryKey var cep: String? = "",
    @ColumnInfo(name = "logradouro")var logradouro: String? = "",
    @ColumnInfo(name = "uf")var uf: String? = "",
    @ColumnInfo(name = "localidade")var localidade: String? = "",
    @ColumnInfo(name = "bairro")var bairro: String? = "",
    @ColumnInfo(name = "num_residencia")var num_residencia: String? = "",
    @ColumnInfo(name = "complemento")var complemento: String? = ""
)