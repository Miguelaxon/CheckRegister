package com.example.checkregister.model

import androidx.lifecycle.LiveData

class RegitroRepository(private val registroDao: RegistroDao) {
    //Contiene todos los datos desde la BBDD
    val listAllRegistro: LiveData<List<Registro>> = registroDao.getAllRegistro()
    //Crea una tarea en la BBDD
    suspend fun insertRegistro(registro: Registro) {
        registroDao.insertRegistro(registro)
    }
    //Elimina la tarea creada en la BBDD
    suspend fun deleteRegistro(registro: Registro) {
        registroDao.deleteRegistro(registro)
    }
    //Elimina todos los datos
    suspend fun deleteAllRegistro() {
        registroDao.deleteAllRegistro()
    }
    //Actualiza los datos
    suspend fun updateRegistro(registro: Registro) {
        registroDao.updateRegistro(registro)
    }
}