package com.example.appos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.appos.R

class AdapterListaOS (val fragment: Fragment): RecyclerView.Adapter<AdapterListaOS.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterListaOS.Holder {
        return Holder(
            LayoutInflater.from(fragment.activity!!.applicationContext)
                .inflate(R.layout.adapter_lista_os, parent, false)
        )
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: AdapterListaOS.Holder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    class Holder(view: View) : RecyclerView.ViewHolder(view) {}
}