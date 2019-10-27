package com.example.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Produto")
data class Produto(
    @ColumnInfo(name = "descricao") var descricao: String? = null,
    @ColumnInfo(name = "observacao") var observacao: String? = null,
    @ColumnInfo(name = "cliente_responsavel") var cliente_responsavel: Cliente? = null
) {
    @PrimaryKey(autoGenerate = true) var idProduto : Int? =null
}