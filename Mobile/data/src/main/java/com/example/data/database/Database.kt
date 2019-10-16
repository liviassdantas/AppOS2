package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.entity.*


@Database(entities = [Cliente::class,
    Empresa::class,
    OS::class,
    Produto::class], version = 1)
abstract class Database : RoomDatabase(){
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




