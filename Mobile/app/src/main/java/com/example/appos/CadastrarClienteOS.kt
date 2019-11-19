package com.example.appos

import Component.CircularProgressButton.customViews.RobinCircularProgressButton
import Util.DateTime
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.appos.util.Prefs
import com.example.appos.view_model.OSView
import com.example.data.entity.*
import com.example.repo.server.CepInitializer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialAutoCompleteTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastrarClienteOS : Fragment() {
    private var edtNumOs: TextInputEditText? = null
    private var edtNomeProdServico: MaterialAutoCompleteTextView? = null
    private var edtDescricaoServico: TextInputEditText? = null
    private var edtResponsavelTecnico: TextInputEditText? = null
    private var edtCPFCliente: TextInputEditText? = null

    private var edtCPFClienteNovo: TextInputEditText? = null
    private var edtNomeCliente: TextInputEditText? = null
    private var edtTelefoneCliente: TextInputEditText? = null
    private var edtCep: TextInputEditText? = null
    private var edtEndereco: TextInputEditText? = null
    private var edtEstado: TextInputEditText? = null
    private var edtCidade: TextInputEditText? = null
    private var edtBairro: TextInputEditText? = null
    private var edtNum_Residencia: TextInputEditText? = null

    private var btnCadastrarCliente: RobinCircularProgressButton? = null

    private val osViewModel: OSView by lazy {
        ViewModelProviders.of(this@CadastrarClienteOS).get(OSView::class.java)
    }

    companion object {
        const val CADASTRAR_OS = 102
        const val CONSULTAR_CLIENTE = 103
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cadastar_os, container, false)

        edtNumOs = view.findViewById(R.id.fragment_cadastrar_OS_edtNumOs)
        edtNomeProdServico = view.findViewById(R.id.fragment_cadastrar_OS_txtServico)
        edtDescricaoServico = view.findViewById(R.id.fragment_cadastrar_OS_edtDescServico)
        edtResponsavelTecnico = view.findViewById(R.id.fragment_cadastrar_OS_edtResponsavel)
        edtCPFCliente = view.findViewById(R.id.fragment_cadastrar_OS_edtCPF)

        osViewModel.osLiveData.observe(this@CadastrarClienteOS, Observer {
            when (it.id) {
                CONSULTAR_CLIENTE -> {
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
                        it.mensagem != null -> {
                            MaterialAlertDialogBuilder(context)
                                .setTitle(getString(R.string.aviso_cadastro))
                                .setMessage(it.mensagem ?: getString(R.string.sem_conexao))
                                .setPositiveButton(
                                    getString(R.string.ok)
                                ) { _, _ -> fragmentManager?.popBackStack() }
                                .show()
                        }
                        else -> {
                            val cliente = it.objeto as Cliente?
                            if (cliente != null) {
                                inserirOrdemServico(cliente)

                            } else {
                                view.findViewById<View>(R.id.layout_cadastrar_cliente).visibility =
                                    View.VISIBLE
                            }
                        }
                    }
                }

                CADASTRAR_OS -> {
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
                        it.mensagem != null -> {
                            MaterialAlertDialogBuilder(context)
                                .setTitle(getString(R.string.aviso_cadastro))
                                .setMessage(it.mensagem ?: getString(R.string.sem_conexao))
                                .setPositiveButton(
                                    getString(R.string.ok)
                                ) { _, _ -> fragmentManager?.popBackStack() }
                                .show()
                        }
                        else -> {
                            MaterialAlertDialogBuilder(context)
                                .setTitle(getString(R.string.cadastrar_ordem))
                                .setMessage("Cadastrado com sucesso")
                                .setPositiveButton(getString(R.string.ok), null)
                                .show()

                        }
                    }
                }
            }
        })



        btnCadastrarCliente = view.findViewById(R.id.activity_cadastrar_os_btnSalvar)

        btnCadastrarCliente?.setOnClickListener {
            btnCadastrarCliente?.startMorphAnimation()
            if (view.findViewById<View>(R.id.layout_cadastrar_cliente).visibility == View.GONE) {

                if (edtCPFCliente?.text.toString().isEmpty().not()) {
                    osViewModel.getCliente(CONSULTAR_CLIENTE, edtCPFCliente?.text.toString())
                }
            } else {
                val cliente = Cliente().apply {

                    cpf_cnpj = edtCPFCliente?.text.toString()
                    nome = edtNomeCliente?.text.toString()
                    telefone = edtTelefoneCliente?.text.toString()
                    cep = edtCep?.text.toString()
                    endereco = edtEndereco?.text.toString()
                    estado = edtEstado?.text.toString()
                    cidade = edtCidade?.text.toString()
                    bairro = edtBairro?.text.toString()
                    num_residencia = edtNum_Residencia?.text.toString()

                }
                val ordem = getOrdem(cliente)
                osViewModel.insertOS(CADASTRAR_OS, ordem)
            }
        }


        edtCep?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                edtCepC: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (edtCepC?.length == 8) {
                    val callRetrofit = CepInitializer().apiCep()
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



        return view
    }

    private fun inserirOrdemServico(cliente: Cliente) {
        val ordem = getOrdem(cliente)
        osViewModel.insertOS(CADASTRAR_OS, ordem)
    }

    private fun getOrdem(cliente: Cliente): OS {
        return OS().apply {
            num_os = edtNumOs?.text.toString().toInt()
            cliente_responsavel = cliente
            produto = Produto().apply{
                descricao = edtNomeProdServico?.text.toString()
            }
            cpfCnpj = context?.let { Prefs(it).getUsuario()?.cpfcnpj }
            data_agendamento = DateTime(DateTime().getData())
            descricao_problema = edtDescricaoServico?.text.toString()
            status_os = StatusEnum.Aguardando_Inicio

        }
    }
}
