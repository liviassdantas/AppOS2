package com.example.appos.adapter

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.appos.R
import com.example.appos.frag.FragListaOS
import com.example.appos.util.Prefs
import com.example.appos.view_model.OSView
import com.example.data.entity.OS
import com.example.data.entity.StatusEnum
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class AdapterListaOS(val fragment: Fragment, val listaOS: MutableList<OS>) :
    RecyclerView.Adapter<AdapterListaOS.Holder>() {

    private val osViewModel: OSView by lazy {
        ViewModelProviders.of(fragment).get(OSView::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterListaOS.Holder {
        return Holder(
            LayoutInflater.from(fragment.context)
                .inflate(R.layout.adapter_lista_os, parent, false)
        )
    }


    override fun getItemCount() = listaOS.size

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        val item = listaOS[p1]
        p0.tipoServico.text = item.descricao_problema.toString()

        p0.numOS.text = String.format(
            fragment.getString(R.string.preencher_numos),
            item.num_os.toString()
        )

        p0.nomeCliente.text = String.format(fragment.getString(R.string.adapter_nome_s), item.cliente_responsavel?.nome)



        p0.atualizarStatus.setOnClickListener {
           val status = when(item.status_os){
               0 -> StatusEnum.Aguardando_Inicio
               1->  StatusEnum.Aguardando_Materiais
               2 -> StatusEnum.Em_Desenvolvimento
               3-> StatusEnum.Aguardando_Liberacao
               else -> StatusEnum.Retirada_Disponivel
           }

            val lista = ArrayList<String>()
            lista.add("Aguardando início do projeto")
            lista.add("Aguardando materiais necessários")
            lista.add("Em Desenvolvimento")
            lista.add("Aguardando Liberação")
            lista.add("Retirada Disponivel")


            val view = LayoutInflater.from(fragment.context).inflate(R.layout.dialog_atualizar_status,null)
            val spinner =  view.findViewById<Spinner>(R.id.dialog_status_selecionar)

            view.findViewById<MaterialTextView>(R.id.dialog_atualizacao_status).text = status.statusToString()

            val spinnerAdpater = ArrayAdapter<String>(fragment.context!!,R.layout.support_simple_spinner_dropdown_item,lista)

            spinnerAdpater.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinner.adapter = spinnerAdpater

            val descricao_atualizacao = view.findViewById<TextInputEditText>(R.id.dialog_descricao_atualizacao)


                MaterialAlertDialogBuilder(fragment.context)
                .setView(view)
                .setNegativeButton("Cancelar",null)
                .setPositiveButton("Atualizar"){dialog,_ ->
                   val statusa = when(spinner.selectedItemId) {
                        0L -> StatusEnum.Aguardando_Inicio
                        1L -> StatusEnum.Aguardando_Materiais
                        2L -> StatusEnum.Em_Desenvolvimento
                        3L -> StatusEnum.Aguardando_Liberacao
                        else -> StatusEnum.Retirada_Disponivel
                    }
                    item.cpfCnpj = fragment.context?.let{Prefs(it).getUsuario()?.cpfcnpj}
                    item.status_os = statusa.value
                    item.observacao_produto = descricao_atualizacao.text.toString()
                    osViewModel.atualizarOS(FragListaOS.ATUALIZAR_OS,item)

                    dialog.dismiss()
                }
                .show()

        }

        p0.contatarCliente.setOnClickListener {
            val uri = Uri.parse("tel:${item.cliente_responsavel?.telefone}")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            fragment.context?.startActivity(intent)

        }

    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView
        val tipoServico: MaterialTextView
        val numOS: MaterialTextView
        val nomeCliente: MaterialTextView
        val atualizarStatus: MaterialTextView
        val contatarCliente: MaterialTextView

        init {
            cardView = view.findViewById(R.id.adapter_lista_os_cardView)
            tipoServico = view.findViewById(R.id.adapter_lista_os_txtTipoServico)
            numOS = view.findViewById(R.id.adapter_lista_os_txtNumOS)
            nomeCliente = view.findViewById(R.id.adapter_lista_os_txtNomeCliente)
            atualizarStatus = view.findViewById(R.id.adapter_lista_os_btnAtualizar)
            contatarCliente = view.findViewById(R.id.adapter_lista_os_btnContatarCliente)
        }

    }
}