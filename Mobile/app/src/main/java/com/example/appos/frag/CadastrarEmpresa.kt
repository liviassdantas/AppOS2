package com.example.appos.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.appos.R
import com.example.data.entity.Endereco
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class CadastrarEmpresa : Fragment() {
    private var edtCpf_Cnpj: TextInputEditText? =null
    private var edtNome_RazaoSocial: TextInputEditText? =null
    private var edtEmail: TextInputEditText? =null
    private var edtTelefone: TextInputEditText? =null
    private var edtCep: TextInputEditText? =null
    private var edtEndereco: TextInputEditText? =null
    private var edtEstado: TextInputEditText? =null
    private var edtCidade: TextInputEditText? =null
    private var edtBairro: TextInputEditText? =null
    private var edtNum_Residencia: TextInputEditText? =null
    private var edtSenha: TextInputEditText? =null
    private var edtConfirmarSenha: TextInputEditText? =null
    private var btnCadastarEmpresa: MaterialButton? =null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cadastrar_empresa, container, false)
        edtCpf_Cnpj = view.findViewById(R.id.fragment_cadastrar_empresa_edtCPF)
        edtNome_RazaoSocial = view.findViewById(R.id.fragment_cadastrar_empresa_edtNomeEmpresa)
        edtEmail = view.findViewById(R.id.fragment_cadastrar_empresa_edtEmail)
        edtTelefone = view.findViewById(R.id.fragment_cadastrar_empresa_edtTelefone)
        edtCep = view.findViewById(R.id.fragment_cadastrar_empresa_edtCep)
        edtEndereco = view.findViewById(R.id.fragment_cadastrar_empresa_edtEndereco)
        edtEstado = view.findViewById(R.id.fragment_cadastrar_empresa_edtEstado)
        edtCidade = view.findViewById(R.id.fragment_cadastrar_empresa_edtCidade)
        edtBairro = view.findViewById(R.id.fragment_cadastrar_empresa_edtBairro)
        edtNum_Residencia = view.findViewById(R.id.fragment_cadastrar_empresa_edtNumResidencia)
        edtSenha = view.findViewById(R.id.fragment_cadastrar_empresa_edtSenha)
        edtConfirmarSenha = view.findViewById(R.id.fragment_cadastrar_empresa_edtCadastrarSenha)
        btnCadastarEmpresa = view.findViewById(R.id.fragment_cadastrar_empresa_btnSalvar)

        return view
    }
}
