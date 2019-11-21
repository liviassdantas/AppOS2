package com.example.repo.controller

import com.example.data.entity.Cliente
import com.example.data.entity.Produto

class OSCadastro {

    var num_os: Int? = null
    var cliente_responsavel: Cliente? = null
    var produto: Produto? = null
    var cpfCnpj: String? = null
    var data_agendamento: String? = null
    var descricao_problema: String? = null
    var tecnicoResp:String? = null
    var status_os: Int? = null
}