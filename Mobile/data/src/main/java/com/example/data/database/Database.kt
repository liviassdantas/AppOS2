package com.example.data.database

import android.content.Context
import androidx.room.*
import androidx.room.Database
import com.example.data.dao.EmpresaDao
import com.example.data.dao.OSDao
import com.example.data.entity.*
import com.example.data.util.Converter

@TypeConverters(Converter::class)
@Database(entities = [Cliente::class,
    Empresa::class,
    OS::class,
    Produto::class], version = 2)
abstract class Database : RoomDatabase(){
    abstract fun empresaDao(): EmpresaDao
    abstract fun osDao(): OSDao

    companion object{

        private var INSTANCE: com.example.data.database.Database? =null

            fun getInstance(context: Context): com.example.data.database.Database? {
                if (INSTANCE == null){
                    synchronized(com.example.data.database.Database::class){
                        INSTANCE = Room.databaseBuilder(context,
                            com.example.data.database.Database::class.java, "bd")
                            .build()
                    }
                }
                return  INSTANCE
            }

        }
    }




