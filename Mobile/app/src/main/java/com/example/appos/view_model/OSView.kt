package com.example.appos.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import android.telephony.TelephonyManager
import br.com.sinapse.aniel.core.entity.exceptions.EAnielException
import br.com.sinapse.aniel.core.entity.service.AnielLocalizacao
import br.com.sinapse.logistiquedata.entity.OrdemServico
import br.com.sinapse.logistiquedata.entity.QualidadeServico
import br.com.sinapse.logistiquedata.enums.DocumentoProducaoStatus
import br.com.sinapse.touchrepo.repository.OSRepository
import br.com.sinapse.touchrepo.util.Prefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class OSView(val app: Application) : AndroidViewModel(app) {
    private val liveOS = MutableLiveData<List<OrdemServico>>()
    private val liveQualidade = MutableLiveData<List<QualidadeServico>>()
    fun retorno(): LiveData<List<OrdemServico>?> = liveOS

    fun retorno_qualidade(): LiveData<List<QualidadeServico>?> = liveQualidade

    fun get(telefone: TelephonyManager?) {
        Executors.newSingleThreadExecutor().submit {
            liveOS.postValue(OSRepository(app, telefone).get())
        }
    }

    fun refresh(telefone: TelephonyManager?) {
        Executors.newSingleThreadExecutor().submit {
            liveOS.postValue(OSRepository(app, telefone).refresh())
        }
    }

    fun refreshSync(telefone: TelephonyManager?) {
            liveOS.postValue(OSRepository(app, telefone).refresh())
    }

    fun save(value: OrdemServico) {
        Executors.newSingleThreadExecutor().submit {
            OSRepository(app, null).save(value)
        }
    }

    fun atualizarStatus(gps: AnielLocalizacao, os: OrdemServico, status: DocumentoProducaoStatus, idNotificacao: Int) =
        GlobalScope.launch(Dispatchers.IO) {
            OSRepository(app, null).get()
                    .filter { o -> o.projetoAniel?.codCt == os.projetoAniel?.codCt && o.projetoAniel?.projeto == os.projetoAniel?.projeto && o.contrato == os.contrato }
                .forEach { s ->
                    s.statusExecucao = status
                    save(s)
                }

            val doc = DocumentoProducaoView(app).atualizarStatus(gps, os, status, idNotificacao)
            val lista: List<OrdemServico> = OSRepository(app, null).get().filter { o -> o.projetoAniel?.codCt == doc.projetoAniel?.codCt && o.projetoAniel?.projeto == doc.projetoAniel?.projeto && o.contrato == doc.contrato } //&& o.dataAgendamento == doc.dataAgendamento
            lista.forEach { s ->
                s.statusExecucao = doc.statusExecucao
                save(s)
            }
            Prefs.setEquipeStatus(getApplication(), status.toString())
        }

    fun getQualidade() = GlobalScope.launch(Dispatchers.IO) {
       liveQualidade.postValue(OSRepository(app,null).getQualidadeServico())
    }

    fun refreshQualidade() = GlobalScope.launch(Dispatchers.IO){
        liveQualidade.postValue(OSRepository(app,null).getQualidadeServicoRefresh())
    }


}