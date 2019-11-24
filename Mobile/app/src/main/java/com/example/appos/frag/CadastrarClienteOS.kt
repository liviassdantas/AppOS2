package com.example.appos.frag

import Component.CircularProgressButton.customViews.RobinCircularProgressButton
import Component.CircularProgressButton.utils.morphAndRevert
import Util.DateTime
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
import com.example.appos.R
import com.example.appos.adapter.AdapterCliente
import com.example.appos.util.Prefs
import com.example.appos.view_model.OSView
import com.example.data.entity.*
import com.example.repo.controller.OSCadastro
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
    private var edtCPFClienteNome: TextInputEditText? = null

    private var edtCPFCliente: TextInputEditText? = null
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

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cadastar_os, container, false)

        edtNumOs = view.findViewById(R.id.fragment_cadastrar_OS_edtNumOs)
        edtDescricaoServico = view.findViewById(R.id.fragment_cadastrar_OS_edtDescServico)
        edtResponsavelTecnico = view.findViewById(R.id.fragment_cadastrar_OS_edtResponsavel)
        edtCPFClienteNome = view.findViewById(R.id.fragment_cadastrar_OS_edtCPF_Nome)


        edtCPFCliente = view.findViewById(R.id.fragment_cadastrar_cliente_edtCPF)
        edtNomeCliente = view.findViewById(R.id.fragment_cadastrar_cliente_edtNomeCliente)
        edtTelefoneCliente = view.findViewById(R.id.fragment_cadastrar_cliente_edtTelefone)
        edtCep = view.findViewById(R.id.fragment_cadastrar_cliente_edtCep)
        edtEndereco = view.findViewById(R.id.fragment_cadastrar_cliente_edtEndereco)
        edtEstado = view.findViewById(R.id.fragment_cadastrar_cliente_edtEstado)
        edtCidade = view.findViewById(R.id.fragment_cadastrar_cliente_edtCidade)
        edtBairro = view.findViewById(R.id.fragment_cadastrar_cliente_edtBairro)
        edtNum_Residencia = view.findViewById(R.id.fragment_cadastrar_cliente_edtNumResidencia)


        btnCadastrarCliente = view.findViewById(R.id.activity_cadastrar_os_btnSalvar)



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
                                    getString(R.string.ok), null
                                )
                                .show()
                        }
                        it.mensagem != null -> {
                            MaterialAlertDialogBuilder(context)
                                .setTitle(getString(R.string.aviso_cadastro))
                                .setMessage(it.mensagem ?: getString(R.string.sem_conexao))
                                .setPositiveButton(
                                    getString(R.string.ok)
                                    , null
                                )
                                .show()
                        }
                        else -> {
                            val clientes = it.objeto as ArrayList<Cliente>?
                            if (!clientes.isNullOrEmpty()) {
                                if (clientes.size == 1) {
                                    inserirOrdemServico(clientes.first())
                                } else {
                                    /*fragmentManager
                                        ?.beginTransaction()
                                        ?.replace(
                                            R.id.container_fragment_principal,
                                            ListaCliente(clientes), "ListaCliente"
                                        )
                                        ?.commit()*/

                                    val filtro = ListaCliente(clientes)
                                    filtro.setCallback(object :ListaCliente.Callback{
                                        override fun onGetCliente(cliente: Cliente) {
                                            inserirOrdemServico(cliente)
                                        }

                                    })

                                    filtro.show(childFragmentManager,"Filtro_Cliente")




                                }

                            } else {
                                view?.let { v ->

                                    MaterialAlertDialogBuilder(context)
                                        .setTitle(getString(R.string.cliente_nao_encontrado))
                                        .setMessage("Cliente : ${edtCPFClienteNome?.text.toString()}\n\n Deseja Cadastrar o cliente")
                                        .setPositiveButton(
                                            getString(R.string.sim)

                                        ) { _, listener ->


                                            val result = edtCPFClienteNome?.text.toString()
                                            val regex = "^[0-9]".toRegex()
                                            if (regex.containsMatchIn(result)) {
                                                edtCPFCliente?.setText(result)
                                            } else {
                                                edtNomeCliente?.setText(result)
                                            }
                                            edtCPFClienteNome?.visibility = View.GONE
                                            v.findViewById<View>(R.id.layout_cadastrar_cliente)
                                                .visibility =
                                                View.VISIBLE
                                        }
                                        .setNegativeButton(R.string.nao, null)
                                        .show()


                                }
                                btnCadastrarCliente?.morphAndRevert()
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
                                    , null
                                )
                                .show()
                        }
                        else -> {

                           MaterialAlertDialogBuilder(this.context)
                                .setTitle(getString(R.string.cadastrar_ordem))
                                .setMessage("Cadastrado com sucesso")
                                .setPositiveButton(getString(R.string.ok)) { dialog, which ->

                                        Log.i("Teste", "Clicou")



                                }
                                .show()


                        }
                    }
                }
            }
            btnCadastrarCliente?.morphAndRevert()


        })


        /*edtNumOs?.error = "Numero da OS não pode ficar em branco"
        edtDescricaoServico?.error = "Descrição do serviço não pode ficar em branco"
        edtCPFCliente?.error = "CPF não pode ficar em branco"
        edtNomeCliente?.error = "Nome não pode ficar me branco"
        edtCep?.error = "CEP não pode ficar em branco"
        edtEndereco?.error = "Endereço não pode ficar em branco"
        edtCidade?.error = "Cidade não pode ficar em branco"
        edtBairro?.error = "Bairro não pode ficar em branco"*/


        /*   MaterialAlertDialogBuilder(context)
               .setTitle(getString(R.string.cadastrar_ordem))
               .setMessage("Cadastrado com sucesso")
               .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                   Log.i("Teste", "Clicou")
               }
               .show()*/


        edtCPFClienteNome?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if ("^[0-9]".toRegex().containsMatchIn(s.toString())) {
                    if ("[A-B,a-b]".toRegex().containsMatchIn(s.toString()) || "^[0-9]+\$".toRegex().containsMatchIn(
                            s.toString()
                        ).not()
                    ) {
                        edtCPFClienteNome?.error = "CPF ou CNPJ não pode conter letras"
                    }
                } else {
                    if ("[0-9]".toRegex().containsMatchIn(s.toString())) {
                        edtCPFClienteNome?.error = "Nome não pode conter números"
                    }
                }


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        btnCadastrarCliente?.setOnClickListener {
            if (edtCPFClienteNome?.error.isNullOrEmpty()) {
                btnCadastrarCliente?.startMorphAnimation()
                if (view.findViewById<View>(R.id.layout_cadastrar_cliente).visibility == View.GONE) {
                    if (edtCPFClienteNome?.text.toString().isEmpty().not()) {
                        osViewModel.getCliente(
                            CONSULTAR_CLIENTE,
                            edtCPFClienteNome?.text.toString()
                        )
                    }
                } else {
                    val cliente = Cliente().apply {

                        cpf_cnpj = edtCPFCliente?.text.toString()
                        nome = edtNomeCliente?.text.toString()
                        endereco = CEP().apply {
                            cep = edtCep?.text.toString()
                            logradouro = edtEndereco?.text.toString()
                            uf = edtEstado?.text.toString()
                            localidade = edtCidade?.text.toString()
                            bairro = edtBairro?.text.toString()
                            num_residencia = edtNum_Residencia?.text.toString()
                        }
                        telefone = edtTelefoneCliente?.text.toString()


                    }
                    val ordem = getOrdem(cliente)
                    osViewModel.insertOS(CADASTRAR_OS, ordem)
                }
            } else {
                if (edtCPFClienteNome?.text.toString().isEmpty()) {
                    edtCPFClienteNome?.error = "Campo não pode ficar em branco"
                }
                MaterialAlertDialogBuilder(context)
                    .setTitle(getString(R.string.aviso_cadastro))
                    .setMessage("Favor verificar os erros do cadastro ")
                    .setPositiveButton(
                        getString(R.string.ok)
                        , null
                    )
                    .show()
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

    private fun getOrdem(cliente: Cliente): OSCadastro {
        return OSCadastro().apply {
            num_os = edtNumOs?.text.toString().toInt()
            cliente_responsavel = cliente
            produto = Produto().apply {
                descricao = edtNomeProdServico?.text.toString()
            }
            cpfCnpj = context?.let { Prefs(it).getUsuario()?.cpfcnpj }
            data_agendamento = DateTime().getData()?.toString()
            descricao_problema = edtDescricaoServico?.text.toString()
            status_os = StatusEnum.Aguardando_Inicio.value

        }
    }
}
