package com.example.videojuegos2023peliculasv2

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cursoradapter.widget.CursorAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videojuegos2023peliculasv2.databinding.ActivityListaPeliculasBinding
import com.example.videojuegos2023peliculasv2.databinding.ItemPeliculaBinding
import com.squareup.picasso.Picasso




class ActivityLista: AppCompatActivity() {

    lateinit var binding: ActivityListaPeliculasBinding
    lateinit var peliculaDBHelper: MiSQLLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        peliculaDBHelper = MiSQLLiteHelper(this)
        val db : SQLiteDatabase = peliculaDBHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM Pelicula",
            null)

        val adaptador = cursorAdapterListView(this, cursor)
        binding.listaPeliculas.adapter = adaptador
        db.close()
    }

    inner class cursorAdapterListView(context : Context, cursor : Cursor) :
        CursorAdapter(context, cursor, FLAG_REGISTER_CONTENT_OBSERVER) {

        override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(context)
            return inflater.inflate(R.layout.item_pelicula, parent, false)
        }

        override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
            val bindingItems = ItemPeliculaBinding.bind(view!!)
            bindingItems.viewTitulo.text = cursor!!.getString(1)
            bindingItems.viewImage.text = cursor!!.getString(2)
            val imageUrl = cursor!!.getString(2)
            bindingItems.viewAnio.text = cursor!!.getString(3)
            bindingItems.viewDescripcion.text = cursor!!.getString(4)

            Picasso.get().load(imageUrl).into(bindingItems.imagePelicula)

            view.setOnClickListener{
                Toast.makeText(this@ActivityLista,
                    "${bindingItems.viewTitulo.text}," +
                        "${bindingItems.viewAnio.text}," +
                            "${bindingItems.imagePelicula}" +
                        "${bindingItems.viewDescripcion.text}",
                    Toast.LENGTH_LONG).show()

            }
        }

    }
}