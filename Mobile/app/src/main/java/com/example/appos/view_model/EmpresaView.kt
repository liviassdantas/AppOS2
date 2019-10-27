package com.example.appos.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.entity.Empresa
import com.example.repo.repository.EmpresaRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class EmpresaView(private val app: Application) : AndroidViewModel(app) {
    val empresaLiveData = MutableLiveData<MutableList<Empresa>>()
    val ret  = MutableLiveData<Long>()
    fun retorno(): LiveData<MutableList<Empresa>?> = empresaLiveData

    fun getAll() = GlobalScope.launch(Dispatchers.IO) {
        val retorno = EmpresaRepo(app).getAllEmpresas()
        empresaLiveData.postValue(retorno.value)
    }

    fun insert(empresa: Empresa) = GlobalScope.launch(Dispatchers.IO) {
        val id = EmpresaRepo(app).insert(empresa)
        ret.postValue(id)
    }

    fun delete(empresa: Empresa) = GlobalScope.launch(Dispatchers.IO) {
        EmpresaRepo(app).delete(empresa)
    }

    fun getByCpfCnpj(cpf_cnpj: String) = GlobalScope.launch(Dispatchers.IO) {
            val retorno = EmpresaRepo(app).selectEmpresaByCpfCnpj(cpf_cnpj)
            empresaLiveData.postValue(mutableListOf(retorno))
        }

//    fun get(telefone: TelephonyManager?) {
//        Executors.newSingleThreadExecutor().submit {
//            liveOS.postValue(OSRepository(app, telefone).get())
//        }
//    }
//
//    fun refresh(telefone: TelephonyManager?) {
//        Executors.newSingleThreadExecutor().submit {
//            liveOS.postValue(OSRepository(app, telefone).refresh())
//        }
//    }
//
//    fun refreshSync(telefone: TelephonyManager?) {
//            liveOS.postValue(OSRepository(app, telefone).refresh())
//    }
//
//    fun save(value: OrdemServico) {
//        Executors.newSingleThreadExecutor().submit {
//            OSRepository(app, null).save(value)
//        }
//    }
//
//    fun atualizarStatus(gps: AnielLocalizacao, os: OrdemServico, status: DocumentoProducaoStatus, idNotificacao: Int) =
//        GlobalScope.launch(Dispatchers.IO) {
//            OSRepository(app, null).get()
//                    .filter { o -> o.projetoAniel?.codCt == os.projetoAniel?.codCt && o.projetoAniel?.projeto == os.projetoAniel?.projeto && o.contrato == os.contrato }
//                .forEach { s ->
//                    s.statusExecucao = status
//                    save(s)
//                }
//
//            val doc = DocumentoProducaoView(app).atualizarStatus(gps, os, status, idNotificacao)
//            val lista: List<OrdemServico> = OSRepository(app, null).get().filter { o -> o.projetoAniel?.codCt == doc.projetoAniel?.codCt && o.projetoAniel?.projeto == doc.projetoAniel?.projeto && o.contrato == doc.contrato } //&& o.dataAgendamento == doc.dataAgendamento
//            lista.forEach { s ->
//                s.statusExecucao = doc.statusExecucao
//                save(s)
//            }
//            Prefs.setEquipeStatus(getApplication(), status.toString())
//        }
//
//    fun getQualidade() = GlobalScope.launch(Dispatchers.IO) {
//       liveQualidade.postValue(OSRepository(app,null).getQualidadeServico())
//    }
//
//    fun refreshQualidade() = GlobalScope.launch(Dispatchers.IO){
//        liveQualidade.postValue(OSRepository(app,null).getQualidadeServicoRefresh())
//    }


}