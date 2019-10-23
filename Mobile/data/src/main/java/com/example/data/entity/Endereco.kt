package com.example.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Endereco")
data class Endereco(
    @PrimaryKey var cep: String? = null,
    @ColumnInfo(name = "rua")var rua: String? = null,
    @ColumnInfo(name = "estado")var estado: String? = null,
    @ColumnInfo(name = "cidade")var cidade: String? = null,
    @ColumnInfo(name = "bairro")var bairro: String? = null,
    @ColumnInfo(name = "num_residencia")var num_residencia: String? = null
)