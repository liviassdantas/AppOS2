package com.example.appos.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appos.CadastrarClienteOS
import com.example.appos.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragListaOS : Fragment() {


    private lateinit var btnAddOS:FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recycler_listar_os, container, false)
            btnAddOS = view.findViewById(R.id.btnAdd)

        btnAddOS.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.addToBackStack("ListaOS")
                ?.add(R.id.container_fragment_principal, CadastrarClienteOS(),"CadastrarOS")
                ?.commit()
        }
        return  view
    }
}