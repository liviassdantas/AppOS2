package com.example.appos.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appos.R
import com.example.appos.adapter.AdapterListaOS
import com.example.appos.util.Prefs
import com.example.appos.view_model.OSView
import com.example.data.entity.OS
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragListaOS : Fragment() {


    private lateinit var btnAddOS: FloatingActionButton
    private lateinit var recycle: RecyclerView
    private val osViewModel: OSView by lazy {
        ViewModelProviders.of(this@FragListaOS).get(OSView::class.java)
    }

    companion object {
        const val LISTA_OS = 401
        const val ATUALIZAR_OS = 402
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        osViewModel.osLiveData.observe(this@FragListaOS, Observer {
            when (it.id) {
                LISTA_OS -> {
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
                            val listaOS = it.objeto as ArrayList<OS>
                            carregarDadosAdapter(listaOS)
                        }
                    }
                }
                ATUALIZAR_OS->{
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
                            MaterialAlertDialogBuilder(context)
                                .setMessage("Ordem de Servico Atualizada com sucesso")
                                .setPositiveButton(
                                    getString(R.string.ok)
                                    , null
                                )
                                .show()
                        }
                    }

                    carregarOS()
                }
            }
        })

    }

    private fun carregarDadosAdapter(listaOS: ArrayList<OS>) {
        recycle.layoutManager =
            GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
        recycle.adapter = AdapterListaOS(this@FragListaOS,listaOS)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recycler_listar_os, container, false)
        btnAddOS = view.findViewById(R.id.btnAdd)
        recycle = view.findViewById(R.id.lista_cards)

        carregarOS()

        btnAddOS.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.addToBackStack("CadastrarOS")
                ?.replace(R.id.container_fragment_principal,
                    CadastrarClienteOS(), "CadastrarOS")
                ?.commit()
        }
        return view
    }

    private fun carregarOS() {
        context?.let { c ->
            val empresa = Prefs(c).getUsuario()
            osViewModel.getOS(LISTA_OS, empresa)
        }

    }
}