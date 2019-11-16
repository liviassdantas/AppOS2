package com.example.appos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.repo.server.CepInitializer
import com.example.data.entity.CEP
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialAutoCompleteTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastrarClienteOS : AppCompatActivity() {
    private var edtNumOs: TextInputEditText? =null
    private var edtNomeProdServico: MaterialAutoCompleteTextView? =null
    private var edtDescricaoServico: TextInputEditText? =null
    private var edtResponsavelTecnico: TextInputEditText? =null
    private var edtCPFCliente: TextInputEditText? =null

    private var edtCPFClienteNovo: TextInputEditText? =null
    private var edtNomeCliente: TextInputEditText? =null
    private var edtTelefoneCliente: TextInputEditText? =null
    private var edtCep: TextInputEditText? = null
    private var edtEndereco: TextInputEditText? = null
    private var edtEstado: TextInputEditText? = null
    private var edtCidade: TextInputEditText? = null
    private var edtBairro: TextInputEditText? = null
    private var edtNum_Residencia: TextInputEditText? = null

    private var btnCadastrarCliente: MaterialButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastraos_cliente)

        edtNumOs = findViewById(R.id.fragment_cadastrar_OS_edtNumOs)
        edtNomeProdServico = findViewById(R.id.fragment_cadastrar_OS_txtServico)
        edtDescricaoServico = findViewById(R.id.fragment_cadastrar_OS_edtDescServico)
        edtResponsavelTecnico = findViewById(R.id.fragment_cadastrar_OS_edtResponsavel)
        edtCPFCliente = findViewById(R.id.fragment_cadastrar_OS_edtCPF)


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

    

    }
}
