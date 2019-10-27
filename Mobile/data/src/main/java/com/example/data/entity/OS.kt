package com.example.data.entity

import Util.DateTime
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OS")
data class OS(
    @ColumnInfo(name = "num_os") var num_os: Int? = null,
    @ColumnInfo(name = "produto")var produto: Produto? = null,
    @ColumnInfo(name = "empresa")var empresa: Empresa? = null,
    @ColumnInfo(name = "data_agendamento")var data_agendamento: DateTime? = null,
    @ColumnInfo(name = "descricao_problema")var descricao_problema: String? = null,
    @ColumnInfo(name = "observacao_produto")var observacao_produto: String? = null,
    @ColumnInfo(name = "valor_servico")var valor_serv: Float? = null,
    @ColumnInfo(name = "status_os")var status_os: StatusEnum? = null
){
    @PrimaryKey(autoGenerate = true) var idOS : Int? =null
}