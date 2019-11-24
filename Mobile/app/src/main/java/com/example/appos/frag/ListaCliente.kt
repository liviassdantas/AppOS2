package com.example.appos.frag

import android.app.Dialog
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.appos.R
import com.example.data.entity.Cliente
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ListaCliente(val listaCliente: ArrayList<Cliente>) : BottomSheetDialogFragment() {
    private lateinit var recycler: ListView
    private lateinit var btnFechar: TextView

    private var callback: Callback? = null

    interface Callback {
        fun onGetCliente(cliente: Cliente)
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView = View.inflate(context,R.layout.filtro_cliente,null)
        dialog.setContentView(contentView)
        recycler = contentView.findViewById(R.id.filtro_cliente_recycler)
        btnFechar = contentView.findViewById(R.id.filtro_cliente_btnFechar)
        val listaNome = ArrayList<String>()
        listaCliente.forEach {
            listaNome.add(String.format("%s - %s", it.cpf_cnpj, it.nome))
        }

        recycler.adapter = ArrayAdapter(context!!,android.R.layout.simple_list_item_1,listaNome )
        recycler.setOnItemClickListener{ adapterView, view, i, l ->
            this.callback?.onGetCliente(listaCliente[adapterView.getItemIdAtPosition(i).toInt()] )
            dismiss()
        }

        btnFechar.setOnClickListener{
            dismiss()
        }
    }


}