package com.sakhura.notasapp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sakhura.notasapp2.adapter.NotasAdapter
import com.sakhura.notasapp2.data.NotasManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onResume() {
        super.onResume()
        actualizarLista()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNotas.layoutManager = LinearLayoutManager(this)
        binding.fabAgregar.setOnClickListener {
            val intent = Intent(this, DetalleNotaActivity::class.java)
            startActivity(intent)
        }
        binding.etBuscar.addTextChangedListener {_, _, _, _ ->
            val texto = binding.etBuscar.text.toString()
            actualizarLista(texto)
            true
        }

        }

    private fun actualizarLista(filtro: String = "") {
        val notasFiltradas = if(filtro.isEmpty()) NotasManager.listaNotas else NotasManager.buscarNotas(filtro)
        binding.rvNotas.adapter = NotasAdapter(notasFiltradas) {
            val intent = Intent(this, DetalleNotaActivity::class.java)
            intent.putExtra("id", it.id)
            startActivity(intent)
        }
        }

    }
