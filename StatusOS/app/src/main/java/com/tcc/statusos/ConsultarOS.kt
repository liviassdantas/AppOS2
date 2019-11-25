package com.tcc.statusos

import Component.CircularProgressButton.customViews.RobinCircularProgressButton
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.tcc.data.entity.OrdemServico
import com.tcc.statusos.viewModel.OsViewModel


class ConsultarOS : Fragment() {

    private lateinit var numOS:TextInputEditText
    private lateinit var cpf:TextInputEditText
    private lateinit var consultar: RobinCircularProgressButton

    private val osViewModel: OsViewModel by lazy {
        ViewModelProviders.of(this@ConsultarOS).get(OsViewModel::class.java)
    }

    companion object{
        const val CONSULTAR_OS = 101
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        osViewModel.osLiveData.observe(this@ConsultarOS, Observer {
            when (it.id) {
                CONSULTAR_OS -> {
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
                        it.objeto != null ->{

                            val ordem = it.objeto as OrdemServico
                            fragmentManager
                                ?.beginTransaction()
                                ?.replace(R.id.container_fragment, FragmentHistorico(ordem), "Historico")
                                ?.commit()
                        }

                    }
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_consultaros, container, false)
        numOS = view.findViewById(R.id.fragment_consultarOS_edtNumOS)
        cpf = view.findViewById(R.id.fragment_consultarOS_edtCPFCliente)
        consultar = view.findViewById(R.id.fragment_consultarOS_consultar)

        consultar.setOnClickListener {
            consultar.startMorphAnimation()
            osViewModel.getOrdemServico(CONSULTAR_OS,numOS.text.toString(), cpf.text.toString())
        }

        return view
    }

}
