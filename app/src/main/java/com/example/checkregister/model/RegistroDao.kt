package com.example.checkregister.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RegistroDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegistro(registro: Registro)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRegistro(registroList: List<Registro>)

    @Update
    suspend fun updateRegistro(registro: Registro)

    @Delete
    suspend fun deleteRegistro(registro: Registro)

    @Query("DELETE FROM registro_table")
    suspend fun deleteAllRegistro()

    @Query("SELECT * FROM registro_table")
    fun getAllRegistro(): LiveData<List<Registro>>

    @Query("SELECT * FROM registro_table WHERE id = :mId")
    fun getRegistro(mId: Int): LiveData<Registro>
}