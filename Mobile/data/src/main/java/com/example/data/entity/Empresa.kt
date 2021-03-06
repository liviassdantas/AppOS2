package com.example.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Empresa")
data class Empresa(
    @ColumnInfo(name = "cpfcnpj") var cpfcnpj: String? = null,
    @ColumnInfo(name = "nome")var nome: String? = null,
    @ColumnInfo(name = "telefone")var telefone: String? = null,
    @ColumnInfo(name = "email")var email: String? = null,
    @ColumnInfo(name = "endereco")var endereco: CEP? = null,
    @ColumnInfo(name = "login")var login: Login? = null
){
    @PrimaryKey(autoGenerate = true) var idEmpresa : Int? = null

}