package com.example.proyectobbdd

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class VM(private val miRepositorio: LibrosRepositorio):ViewModel() {
    val listaLibros: LiveData<MutableList<Libro>> = miRepositorio.listaLibros.asLiveData()
    lateinit var miLibro:LiveData<Libro>

    fun insertar(miLibro: Libro) = viewModelScope.launch {
        miRepositorio.insertar(miLibro)
    }

    fun buscarPorId(id:Int) = viewModelScope.launch {
        miLibro=miRepositorio.buscarPorId(id).asLiveData()
    }

    fun borrar(miLibro: Libro) = viewModelScope.launch {
        miRepositorio.borrar(miLibro)
    }

    fun actualizar(miLibro: Libro) = viewModelScope.launch {
        miRepositorio.actualizar(miLibro)
    }

    fun borrarPorId(id:Int) = viewModelScope.launch {
        miRepositorio.borrarPorId(id)
    }
}

class LibrosViewModelFactory (private val miRepositorio: LibrosRepositorio) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>):T{
        if (modelClass.isAssignableFrom(VM::class.java)){
            @Suppress("UNCHECKED_CAST")
            return VM(miRepositorio) as T
        }
        throw IllegalArgumentException("Clase ViewModel no reconocida")
    }
}