package com.sakhura.notasapp2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sakhura.notasapp2.data.NotasManager
import com.sakhura.notasapp2.databinding.ActivityDetalleNotaBinding
import com.sakhura.notasapp2.model.Nota
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetalleNotaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleNotaBinding
    private var notaId: Long? = null
    private var notaExistente: Nota? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleNotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar toolbar si existe
        binding.toolbar?.let {
            setSupportActionBar(it)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            it.setNavigationOnClickListener { finish() }
        }

        // Obtener datos de la nota si es una edición
        notaId = intent.getLongExtra("id", -1).takeIf { it != -1L }
        notaExistente = notaId?.let { NotasManager.obtenerNota(it) }

        // Cargar datos existentes si es una edición
        notaExistente?.let { nota ->
            binding.etTitulo.setText(nota.titulo)
            binding.etContenido.setText(nota.contenido)

            // Actualizar información de última modificación
            binding.tvInfo?.text = "Última modificación: ${nota.fechaCreacion}"
        }

        // Configurar botón guardar
        binding.btnGuardar.setOnClickListener {
            guardarNota()
        }

        // Configurar botón eliminar
        binding.btnEliminar.setOnClickListener {
            eliminarNota()
        }
    }

    private fun guardarNota() {
        val titulo = binding.etTitulo.text.toString().trim()
        val contenido = binding.etContenido.text.toString().trim()

        // Validaciones básicas
        if (titulo.isEmpty()) {
            Toast.makeText(this, "El título no puede estar vacío", Toast.LENGTH_SHORT).show()
            binding.etTitulo.requestFocus()
            return
        }

        if (contenido.isEmpty()) {
            Toast.makeText(this, "El contenido no puede estar vacío", Toast.LENGTH_SHORT).show()
            binding.etContenido.requestFocus()
            return
        }

        try {
            if (notaExistente != null) {
                // Actualizar nota existente
                NotasManager.actualizarNota(notaExistente!!.id, titulo, contenido)
                Toast.makeText(this, "Nota actualizada", Toast.LENGTH_SHORT).show()
            } else {
                // Crear nueva nota
                val fecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                val nuevaNota = Nota(titulo = titulo, contenido = contenido, fechaCreacion = fecha)
                NotasManager.agregarNota(nuevaNota)
                Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show()
            }
            finish()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al guardar la nota", Toast.LENGTH_SHORT).show()
        }
    }

    private fun eliminarNota() {
        notaId?.let { id ->
            try {
                NotasManager.eliminarNota(id)
                Toast.makeText(this, "Nota eliminada", Toast.LENGTH_SHORT).show()
                finish()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al eliminar la nota", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(this, "No se puede eliminar una nota nueva", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}