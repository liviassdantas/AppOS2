package com.example.data.entity


import Util.DateTime
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cliente")
data class Cliente(
    @ColumnInfo(name = "cpfcnpj") var cpf_cnpj: String? = null,
    @ColumnInfo(name = "nome")var nome: String? = null,
    @ColumnInfo(name = "data_nasc")var data_nasc: String? = null,
    @ColumnInfo(name = "endereco")var endereco: CEP? = null,
    @ColumnInfo(name = "telefone")var telefone: String? = null,
    @ColumnInfo(name = "email")var email: String? = null
    ){

    @PrimaryKey(autoGenerate = true) var idCliente : Int? =null
}