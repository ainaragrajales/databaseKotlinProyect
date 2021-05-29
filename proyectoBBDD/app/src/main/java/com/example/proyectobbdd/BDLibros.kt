package com.example.proyectobbdd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Libro::class), version=1, exportSchema = false)
abstract class BDLibros:RoomDatabase() {

    abstract fun miDAO(): LibrosDAO

    companion object{
        @Volatile
        private var INSTANCE: BDLibros?=null

        fun getDatabase (context: Context): BDLibros{
            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    BDLibros::class.java,
                    "mislibros_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }

}