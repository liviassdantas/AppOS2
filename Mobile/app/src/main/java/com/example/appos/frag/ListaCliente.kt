package com.example.appos.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appos.R
import com.example.appos.adapter.AdapterCliente
import com.example.data.entity.Cliente

class ListaCliente(val listaCliente: ArrayList<Cliente>) : Fragment() {
    private lateinit var recycle: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_lista_cliente, container, false)

        recycle = v.findViewById(R.id.lista_cliente_recycle)
        recycle.layoutManager =
            GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
        recycle.adapter = AdapterCliente(this@ListaCliente, listaCliente)

        return v
    }


}