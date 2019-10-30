package com.example.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Empresa")
data class Empresa(
    @ColumnInfo(name = "cpf_cnpj") var cpf_cnpj: String? = null,
    @ColumnInfo(name = "nome")var nome: String? = null,
    @ColumnInfo(name = "telefone")var telefone: String? = null,
    @ColumnInfo(name = "email")var email: String? = null,
    @ColumnInfo(name = "cep")var cep: CEP? = null,
    @ColumnInfo(name = "senha")var senha: String? = null
){
    @PrimaryKey(autoGenerate = true) var idEmpresa : Int? = null

}