package com.example.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.data.entity.OS


@Dao
interface OSDao {
//    @Query("SELECT * FROM OS")
//    fun getAll():LiveData<MutableList<OS>>
//
//    @Query("SELECT * FROM OS WHERE empresa = :empresa")
//    fun getAllOrdemServico(empresa: String): LiveData<MutableList<OS>>
}