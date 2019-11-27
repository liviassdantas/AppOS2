package com.tcc.statusos

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.textview.MaterialTextView
import com.tcc.data.entity.OrdemServico
import kotlinx.android.synthetic.main.card_status_os.*


class FragmentHistorico(val ordem :OrdemServico) : Fragment() {
    private lateinit var txtNumOS: MaterialTextView
    private lateinit var txtEmpresa: MaterialTextView
    private lateinit var  txtTipoServico: MaterialTextView
    private lateinit var  txtNomeCliente: MaterialTextView
    private lateinit var statusImagem: ImageView
    private lateinit var statusNome: MaterialTextView
    private lateinit var statusDescricao: MaterialTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_historico,container, false)
        txtNumOS = view.findViewById(R.id.historico_num_os)
        txtEmpresa = view.findViewById(R.id.historico_empresa)
        txtTipoServico = view.findViewById(R.id.hisotrico_tipo_servico)
        txtNomeCliente = view.findViewById(R.id.historico_nome_cliente)
        statusImagem = view.findViewById(R.id.icone_iniciar)
        statusNome = view.findViewById(R.id.status_os_Enum_iniciar_atendimento)
        statusDescricao = view.findViewById(R.id.status_os_Descricao_iniciar_atendimento)

        txtNumOS.text = String.format(getString(R.string.n_os_historico),ordem.num_os.toString())
        txtEmpresa.text = ordem.empresa?.nome.toString()
        txtTipoServico.text = ordem.descricao_problema.toString()
        txtNomeCliente.text = ordem.cliente_responsavel?.nome.toString()

        when(ordem.status_os){
            0 -> {
                //statusImagem.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_play))
                statusNome.text= getString(R.string.aguardando_inicio)
//                statusDescricao.text = ordem.observacao_produto.toString()
            }
            1 -> {
                statusImagem.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_settings))
                statusNome.text= getString(R.string.aguardando_material)
                statusDescricao.text = ordem.observacao_produto.toString()
            }
            2 -> {
                statusImagem.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_settings))
                statusNome.text= getString(R.string.em_desenvolvimento)
                statusDescricao.text = ordem.observacao_produto.toString()
            }
            3 -> {
                statusImagem.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_settings))
                statusNome.text= getString(R.string.aguardando_liberacao)
                statusDescricao.text = ordem.observacao_produto.toString()
            }
            4 -> {
                statusImagem.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_cheked))
                statusNome.text= getString(R.string.aguardando_retirada)
                statusDescricao.text = ordem.observacao_produto.toString()
            }else ->{
                statusImagem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_warning))
                statusNome.text = getString(R.string.problemas_atualizacao)
                statusDescricao.text = getString(R.string.problemas_atualizacao_descricao)
        }

        }


        return view
    }


}
