package com.example.appos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.appos.util.Prefs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView

class Principal : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var toolbar: Toolbar? = null
    lateinit var drawerlayout: DrawerLayout
    lateinit var nvgview: NavigationView
    var activity = Activity()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navdrawer_principal)

        toolbar = findViewById(R.id.navigation_toolbar)
        setSupportActionBar(toolbar)
        drawerlayout = findViewById(R.id.nvdrawerLayout)
        nvgview = findViewById(R.id.activity_principal_navigationView)
        nvgview.itemIconTintList= null

        val toogle = ActionBarDrawerToggle(
            this, drawerlayout, toolbar, R.string.abrir_drawer, R.string.fechar_drawer
        )
        drawerlayout.addDrawerListener(toogle)
        toogle.syncState()

        nvgview.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
           R.id.menu_sair ->{
               startActivity(Intent(applicationContext, Login::class.java))
           }
           R.id.menu_relatar_problema ->{
               MaterialAlertDialogBuilder(this@Principal)
                   .setTitle(getString(R.string.ainda_nao_concluido))
                   .setMessage(getString(R.string.desenvolvendo_recurso))
                   .setPositiveButton(getString(R.string.ok),null)
                   .show()
           }
       }
        return true
    }


}