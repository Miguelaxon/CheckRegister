package com.example.checkregister.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegistroViewModel(appication: Application): AndroidViewModel(appication) {
    private val repository: RegitroRepository
    val allRegistro: LiveData<List<Registro>>

    init {
        val registroDao = RegistroDataBase.getDataBase(appication).getRegistroDao()
        repository = RegitroRepository(registroDao)
        allRegistro = repository.listAllRegistro
    }

    fun insertRegistro(registro: Registro) = viewModelScope.launch {
        repository.insertRegistro(registro)
    }

    fun deleteAllRegistro() = viewModelScope.launch {
        repository.deleteAllRegistro()
    }

    private var selectedTask: MutableLiveData<Registro> = MutableLiveData()

    fun selected(registro: Registro?) {
        selectedTask.value = registro
    }

    fun selectedItem(): LiveData<Registro> = selectedTask

    fun updateTask(registro: Registro) = viewModelScope.launch {
        repository.updateRegistro(registro)
    }
}