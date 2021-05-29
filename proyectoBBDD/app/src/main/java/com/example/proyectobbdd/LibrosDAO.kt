package com.example.proyectobbdd

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LibrosDAO {
    @Query("SELECT * FROM tabla_mislibros ORDER BY id ASC")
    fun mostrarTodos(): Flow<MutableList<Libro>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(miLibro: Libro)

    @Query ("DELETE FROM tabla_mislibros")
    suspend fun borrarTodo()

    @Query ("SELECT * FROM tabla_mislibros WHERE id LIKE :id")
    fun buscarPorId(id: Int):Flow<Libro>

    @Update
    suspend fun actualizar(miLibro: Libro)

    @Delete
    suspend fun borrar(miLibro: Libro)

    @Query("DELETE FROM tabla_mislibros WHERE id LIKE :id")
    suspend fun borrarPorId(id:Int)
}