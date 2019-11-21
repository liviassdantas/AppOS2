package com.example.appos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.appos.R
import com.example.appos.frag.ListaCliente
import com.example.data.entity.Cliente
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class AdapterCliente(val frag: Fragment, val listaCliente: MutableList<Cliente>):RecyclerView.Adapter<AdapterCliente.Holder>(){

    var callback : ClienteCallback? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return  Holder(LayoutInflater.from(frag.context).inflate(R.layout.adapter_lista_cliente,parent,false))
    }

    override fun getItemCount(): Int = listaCliente.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val cliente = listaCliente[position]

        holder.cpf_cnpjCliente.text = cliente.cpf_cnpj
        holder.nomeCliente.text = cliente.nome
        holder.telefoneCliente.text = cliente.telefone
        holder.enderecocliente.text = cliente.endereco?.logradouro?: ""
        holder.cardView.setOnClickListener {
            frag.fragmentManager?.popBackStack()
            callback?.clienteSelecionado(cliente)
        }
    }



    inner class Holder(view: View) : RecyclerView.ViewHolder(view){
        val cardView:MaterialCardView = view.findViewById(R.id.adapter_lista_cliente_cardView)
        val cpf_cnpjCliente:MaterialTextView = view.findViewById(R.id.fragment_cliente_lista_cpf)
        val nomeCliente:MaterialTextView = view.findViewById(R.id.fragment_cliente_lista_nome)
        val telefoneCliente:MaterialTextView = view.findViewById(R.id.fragment_cliente_lista_Telefone)
        val enderecocliente:MaterialTextView = view.findViewById(R.id.fragment_cliente_lista_endereco)

    }

    interface ClienteCallback {
        fun clienteSelecionado(cliente: Cliente)
    }
}