package com.example.videojuegos2023peliculasv2



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.videojuegos2023peliculasv2.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var peliculaDBHelper: MiSQLLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        peliculaDBHelper = MiSQLLiteHelper(this)

        binding.btnGuardar.setOnClickListener{
            if(binding.txtTitulo.text.isNotBlank() && binding.txtImage.text.isNotBlank() && binding.txtYear.text.isNotBlank() && binding.txtDescripcion.text.isNotBlank()){
                peliculaDBHelper.agregarDato(binding.txtTitulo.text.toString(), binding.txtImage.text.toString(), binding.txtYear.text.toString(), binding.txtDescripcion.text.toString())
                binding.txtTitulo.text.clear()
                binding.txtImage.text.clear()
                binding.txtYear.text.clear()
                binding.txtDescripcion.text.clear()
                Toast.makeText(this, "Guardado",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "No se ha podido guardar",Toast.LENGTH_LONG).show()
            }
        }

        binding.btnListaPeliculas.setOnClickListener{
            val intentListView = Intent(this, ActivityLista:: class.java)
            startActivity(intentListView)
        }
        binding.btnEliminar.setOnClickListener{
            var cantidad = 0

            if(binding.txtId.text.isNotBlank()){
                cantidad = peliculaDBHelper.eliminarDato(binding.txtId.text.toString().toInt())
                binding.txtId.text.clear()

            }else{
                Toast.makeText(this, "Datos borrados: $cantidad",Toast.LENGTH_LONG).show()
            }
        }
    }
}