package com.example.appos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appos.frag.FragLogin

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment_principal, FragLogin(), "Login")
            .commit()
    }
}
