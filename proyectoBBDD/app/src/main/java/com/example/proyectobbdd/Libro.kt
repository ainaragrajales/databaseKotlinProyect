package com.example.proyectobbdd

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="tabla_libros")
data class Libro(@PrimaryKey (autoGenerate = true) var id:Int=0,
                 @NonNull @ColumnInfo (name="titulo") var titulo:String,
                 @NonNull @ColumnInfo (name="autor") var autor:String,
                 @NonNull @ColumnInfo (name="genero") var genero:String,
                 @NonNull @ColumnInfo (name="fechaPublicacion") var fechaPublicacion:String,
                 @NonNull @ColumnInfo (name="imagen") var urlImagen:String)
