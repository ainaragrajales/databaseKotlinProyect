package com.example.proyectobbdd

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


class LibrosRepositorio(val miDAO: LibrosDAO) {
    val listaLibros: Flow<MutableList<Libro>> =miDAO.mostrarTodos()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertar(miLibro: Libro){
        miDAO.insertar(miLibro)
    }

    fun buscarPorId(id:Int): Flow<Libro> {
        return miDAO.buscarPorId(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun borrar(miLibro: Libro){
        miDAO.borrar(miLibro)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun actualizar(miLibro: Libro){
        miDAO.actualizar(miLibro)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun borrarPorId(id:Int){
        miDAO.borrarPorId(id)
    }
}