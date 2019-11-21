package com.example.appos.frag

import Component.CircularProgressButton.customViews.RobinCircularProgressButton
import Component.CircularProgressButton.utils.morphAndRevert
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.appos.Principal

import com.example.appos.R
import com.example.appos.util.Prefs
import com.example.appos.view_model.EmpresaView
import com.example.data.entity.Empresa
import com.example.repo.util.ResponseViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class FragLogin : Fragment() {
    private var edtLogin: TextInputEditText? = null
    private var edtSenha: TextInputEditText? = null
    private var btnEntrar: RobinCircularProgressButton? = null
    private var btnCadastrarEmpresa: MaterialButton? =null

    private val empresaViewModel: EmpresaView by lazy {
        ViewModelProviders.of(this@FragLogin).get(EmpresaView::class.java)
    }

    companion object {
        const val RECEBER_EMPRESA = 102
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        empresaViewModel.empresaLiveData.observe(this@FragLogin, Observer {
            when (it.id) {
                RECEBER_EMPRESA -> {
                    when {
                        it.exception != null -> {
                            MaterialAlertDialogBuilder(context)
                                .setTitle(getString(R.string.erro_cadastro))
                                .setMessage(
                                    it.exception?.message ?: getString(R.string.sem_conexao)
                                )
                                .setPositiveButton(
                                    getString(R.string.ok)
                                ) { _, _ -> fragmentManager?.popBackStack() }
                                .show()
                        }
                        it.mensagem != null -> MaterialAlertDialogBuilder(context)
                            .setTitle(getString(R.string.aviso_cadastro))
                            .setMessage(it.mensagem ?: getString(R.string.sem_conexao))
                            .setPositiveButton(
                                getString(R.string.ok)
                                ,null)
                            .show()
                        else -> {
                            it.objeto?.let { objeto ->
                                val empresa = (objeto as Empresa)
                                Prefs(context!!).setUsuario(empresa)
                                startActivity(Intent(activity,Principal::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                            }
                        }
                    }
                    btnEntrar?.morphAndRevert(500)
                }
            }

        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        edtLogin = view.findViewById(R.id.activity_login_edtLogin)
        edtSenha = view.findViewById(R.id.activity_login_edtSenha)
        btnEntrar = view.findViewById(R.id.activity_login_btnEntrar)
        btnCadastrarEmpresa = view.findViewById(R.id.activity_login_btnSemCadastro)


        btnEntrar?.setOnClickListener {
            val cpf_cnpj = edtLogin?.text.toString()
            val senha = edtSenha?.text.toString()
            empresaViewModel.getLogin(RECEBER_EMPRESA, cpf_cnpj, senha)
            btnEntrar?.startAnimation()
        }

        btnCadastrarEmpresa?.setOnClickListener {


            fragmentManager?.beginTransaction()
                ?.addToBackStack("Login")
                ?.replace(R.id.container_fragment_login, CadastrarEmpresa(), "CadastrarEmpresa")
                ?.commit()
            if(edtLogin?.text.toString().isEmpty().not()){
                edtLogin?.text?.clear()
            }
            if(edtSenha?.text.toString().isEmpty().not()){
                edtSenha?.setText("")
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        empresaViewModel.limparDados()
    }
}

