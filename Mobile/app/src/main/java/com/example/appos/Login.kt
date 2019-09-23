package com.example.appos

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.appos.Fragments.CadastrarEmpresa

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val btnCadastrar = findViewById<Button>(R.id.activity_login_btnSemCadastro).setOnClickListener{
            this@Login.supportFragmentManager
                .beginTransaction()
                .replace(R.id.ContainerFragment, CadastrarEmpresa())
                .commit()
        }
    }
}
