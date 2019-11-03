package com.example.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.entity.Empresa

@Dao
interface EmpresaDao {
    @Insert
    fun insert(empresa: Empresa):Long

    @Delete
    fun delete(empresa: Empresa)

    @Query("SELECT * FROM Empresa WHERE cpfcnpj = :cpf_cnpj ")
    fun selectEmpresaBycpfCnpj(cpf_cnpj: String): Empresa

    @Query("SELECT * FROM Empresa")
    fun getAllEmpresas(): LiveData<MutableList<Empresa>>


}
