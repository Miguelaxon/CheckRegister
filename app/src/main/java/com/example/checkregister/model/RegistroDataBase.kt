package com.example.checkregister.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Registro::class], version = 1)
abstract class RegistroDataBase : RoomDatabase(){
    abstract fun getRegistroDao(): RegistroDao
    companion object {
        @Volatile
        private var INSTANCE: RegistroDataBase? = null
        fun getDataBase(context: Context): RegistroDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    RegistroDataBase::class.java, "registroDB")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}