package com.caballero.leo.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.caballero.leo.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Ejercicio01 : AppCompatActivity() {
    private lateinit var binding: ActivityEjercicio01Binding
    private lateinit var adapter: ProfesorAdapter
    private val profesorService: ProfesorService by lazy {
        Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfesorService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            try {
                val response = profesorService.getProfesores()
                val profesores = response.teachers
                setupRecyclerView(profesores)
            } catch (e: Exception) {
                Log.e("Ejercicio01", "Error al cargar datos: ${e.message}", e)
                Toast.makeText(this@Ejercicio01, "Error al cargar datos", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupRecyclerView(profesores: List<Profesor>) {
        adapter = ProfesorAdapter(profesores,
            onClick = { profesor ->
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${profesor.telefono}")
                }
                startActivity(intent)
            },
            onLongClick = { profesor ->
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${profesor.email}")
                }
                startActivity(intent)
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}
