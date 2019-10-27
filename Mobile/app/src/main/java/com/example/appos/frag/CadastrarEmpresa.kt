package com.example.appos.frag

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.appos.R
import com.example.appos.buscaCEPRetrofit.RetrofitInitializer
import com.example.appos.util.Mask
import com.example.appos.view_model.EmpresaView
import com.example.data.entity.CEP
import com.example.data.entity.Empresa
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CadastrarEmpresa : Fragment() {
    private var edtCpf_Cnpj: TextInputEditText? = null
    private var edtNome_RazaoSocial: TextInputEditText? = null
    private var edtEmail: TextInputEditText? = null
    private var edtTelefone: TextInputEditText? = null
    private var edtCep: TextInputEditText? = null
    private var edtEndereco: TextInputEditText? = null
    private var edtEstado: TextInputEditText? = null
    private var edtCidade: TextInputEditText? = null
    private var edtBairro: TextInputEditText? = null
    private var edtNum_Residencia: TextInputEditText? = null
    private var edtSenha: TextInputEditText? = null
    private var edtConfirmarSenha: TextInputEditText? = null
    private var btnCadastarEmpresa: MaterialButton? = null

    private val empresaViewModel: EmpresaView by lazy {
        ViewModelProviders.of(this@CadastrarEmpresa).get(EmpresaView::class.java)
    }

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

//        edtCpf_Cnpj?.addTextChangedListener(Mask.mask("###.###.###-##", edtCpf_Cnpj!!))

        btnCadastarEmpresa?.setOnClickListener{

            if(edtCpf_Cnpj?.text.isNullOrBlank()){
                edtCpf_Cnpj?.error = getString(R.string.erro_cpf_cnpj)
            }else if(edtNome_RazaoSocial?.text.isNullOrBlank()){
                edtNome_RazaoSocial?.error = getString(R.string.erro_nome_razao)
            }else if (edtTelefone?.text.isNullOrBlank()&&edtEmail?.text.isNullOrBlank()){
                edtTelefone?.error = getString(R.string.erro_meio_contato)
            }else if (edtCep?.text.isNullOrBlank()){
                edtCep?.error = getString(R.string.erro_cep)
            }else if (edtSenha?.text.isNullOrBlank()){
                edtSenha?.error = getString(R.string.erro_senha)
            }else if(edtConfirmarSenha?.text.isNullOrBlank()){
                edtConfirmarSenha?.error = getString(R.string.erro_confirmar_sneha)
            }else {

                val empresa = Empresa().apply {
                    cpf_cnpj = edtCpf_Cnpj?.text.toString()
                    nome = edtNome_RazaoSocial?.text.toString()
                    email = edtEmail?.text.toString()
                    telefone = edtTelefone?.text.toString()
                    endereco = edtEndereco?.text.toString()
                    cep = edtCep?.text.toString()
                    cidade = edtCidade?.text.toString()
                    bairro = edtBairro?.text.toString()
                    estado = edtEstado?.text.toString()
                    num_residencia = edtNum_Residencia?.text.toString()
                    senha = edtConfirmarSenha?.text.toString()
                }
                salvaDados(empresa)
            }
        }

        edtCep?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(
                edtCepC: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (edtCepC?.length == 8) {
                    val callRetrofit = RetrofitInitializer().apiRetrofitService()
                        .getEnderecoByCEP(edtCepC.toString())

                    callRetrofit.enqueue(object : Callback<CEP> {
                        override fun onFailure(call: Call<CEP>?, t: Throwable?) {
                            Log.e("TextError", t?.message!!)
                        }

                        override fun onResponse(call: Call<CEP>, response: Response<CEP>) {
                            response.let {
                                val cepR: CEP? = it.body()

                                edtCep?.setText(cepR?.cep.toString()).toString()
                                edtBairro?.setText(cepR?.bairro.toString()).toString()
                                edtCidade?.setText(cepR?.localidade.toString()).toString()
                                edtEndereco?.setText(cepR?.logradouro.toString()).toString()
                                edtEstado?.setText(cepR?.uf.toString()).toString()

                            }
                        }

                    })
                }

            }

        })


        empresaViewModel.ret.observe(this@CadastrarEmpresa, Observer {
                MaterialAlertDialogBuilder(context)
                    .setTitle("Cadastrado com Sucesso")
                    .setMessage("Empresa cadastrada com sucesso")
                    .setPositiveButton("Ok", null)
                    .show()
       })

        return view
    }

    fun salvaDados(empresa: Empresa) {
         empresaViewModel.insert(empresa)
    }
}
