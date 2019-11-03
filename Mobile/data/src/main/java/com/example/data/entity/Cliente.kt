package com.example.data.entity


import Util.DateTime
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cliente")
data class Cliente(
    @ColumnInfo(name = "cpfcnpj") var cpf_cnpj: String? = null,
    @ColumnInfo(name = "nome")var nome: String? = null,
    @ColumnInfo(name = "telefone")var telefone: String? = null,
    @ColumnInfo(name = "cep")var cep: String? = null,
    @ColumnInfo(name = "data_nasc")var data_nasc: DateTime? = null,
    @ColumnInfo(name = "email")var email: String? = null,
    @ColumnInfo(name = "endereco")var endereco: String? = null,
    @ColumnInfo(name = "estado")var estado: String? = null,
    @ColumnInfo(name = "cidade")var cidade: String? = null,
    @ColumnInfo(name = "bairro")var bairro: String? = null,
    @ColumnInfo(name = "num_residencia")var num_residencia: String? = null,
    @ColumnInfo(name = "senha")var senha: String? = null
){
    @PrimaryKey(autoGenerate = true) var idCliente : Int? =null
}