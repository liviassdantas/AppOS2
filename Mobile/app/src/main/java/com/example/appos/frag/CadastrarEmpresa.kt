package com.example.appos.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.appos.R

class CadastrarEmpresa : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cadastrar_empresa, container, false)

        /*view.findViewById<Button>(R.id.activity_login_btnSemCadastro).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.container_fragment_principal, CadastrarEmpresa(), "Login")
                ?.commit()
        }*/

        return view
    }
}