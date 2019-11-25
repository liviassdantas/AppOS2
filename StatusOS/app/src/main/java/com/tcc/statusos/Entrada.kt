package com.tcc.statusos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Entrada : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrada)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment,ConsultarOS(),"C")
            .commit()
    }
}
