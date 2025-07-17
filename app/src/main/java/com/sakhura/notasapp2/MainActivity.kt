package com.sakhura.notasapp2

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sakhura.notasapp2.adapter.NotasAdapter
import com.sakhura.notasapp2.data.NotasManager
import com.sakhura.notasapp2.databinding.ActivityMainBinding
import com.sakhura.notasapp2.model.Nota

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

        setupRecyclerView()
        setupSearchFunctionality()
        setupFabButton()
    }

    private fun setupRecyclerView() {
        binding.rvNotas.layoutManager = LinearLayoutManager(this)
        actualizarLista()
    }

    private fun setupFabButton() {
        binding.fabAgregar?.setOnClickListener {
            val intent = Intent(this, DetalleNotaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupSearchFunctionality() {
        // Buscar el campo de búsqueda por findViewById como alternativa
        val etBuscar = findViewById<com.google.android.material.textfield.TextInputEditText>(
            com.sakhura.notasapp2.R.id.etBuscar
        )

        etBuscar?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val texto = s.toString()
                actualizarLista(texto)
            }
        })
    }

    private fun actualizarLista(filtro: String = "") {
        val notasFiltradas = if(filtro.isEmpty()) {
            NotasManager.listaNotas
        } else {
            NotasManager.buscarNotas(filtro)
        }

        binding.rvNotas.adapter = NotasAdapter(notasFiltradas) { nota ->
            val intent = Intent(this, DetalleNotaActivity::class.java)
            intent.putExtra("id", nota.id)
            startActivity(intent)
        }
    }
}