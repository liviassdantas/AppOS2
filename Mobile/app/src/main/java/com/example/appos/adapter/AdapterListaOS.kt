package com.example.appos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.appos.R
import com.example.data.entity.OS
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class AdapterListaOS(val fragment: Fragment, val listaOS: MutableList<OS>) :
    RecyclerView.Adapter<AdapterListaOS.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterListaOS.Holder {
        return Holder(
            LayoutInflater.from(fragment.activity!!.applicationContext)
                .inflate(R.layout.adapter_lista_os, parent, false)
        )
    }


    override fun getItemCount()= listaOS.size

    override fun onBindViewHolder(p0: Holder, p1: Int) {
       val item = listaOS[p1]
       p0.tipoServico.text = item.descricao_problema.toString()
       p0.numOS.text = String.format("%s", fragment.context?.getString(R.string.preencher_numos), item.num_os.toString())

    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView
        val tipoServico: MaterialTextView
        val numOS: MaterialTextView
        val atualizarStatus: MaterialTextView
        val contatarCliente: MaterialTextView

        init {
            cardView = view.findViewById(R.id.adapter_lista_os_cardView)
            tipoServico = view.findViewById(R.id.adapter_lista_os_txtTipoServico)
            numOS = view.findViewById(R.id.adapter_lista_os_txtNumOS)
            atualizarStatus = view.findViewById(R.id.adapter_lista_os_btnAtualizar)
            contatarCliente = view.findViewById(R.id.adapter_lista_os_btnContatarCliente)
        }

    }
}