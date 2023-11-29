package com.example.videojuegos2023peliculasv2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MiSQLLiteHelper(context: Context) : SQLiteOpenHelper(
    context, "Peliculas.db",null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE Pelicula" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT, " +
                "urlImagen TEXT, " +
                "anio TEXT, " +
                "descripcion TEXT)"
        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS Pelicula"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun agregarDato(titulo: String, urlImagen: String, anio: String, descripcion: String){
val datos = ContentValues()
        datos.put("titulo", titulo)
        datos.put("urlImagen", urlImagen)
        datos.put("anio", anio)
        datos.put("descripcion", descripcion)

        val db = this.writableDatabase
        db.insert("Pelicula", null, datos)
        db.close()
    }

    fun eliminarDato(_id: Int): Int{
        val args = arrayOf(_id.toString())

        val db = this.writableDatabase
        val borrados = db.delete("Pelicula", "_id = ?", args)
        //db.execSQL("DELETE FROM Pelicula WHERE _id = ?", args)
        db.close()
        return borrados
    }
}