package com.sakhura.notasapp2

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sakhura.notasapp2.data.NotasManager
import com.sakhura.notasapp2.model.Nota
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetalleNotaActivity : AppCompatActivity(){
    private lateinit var binding: ActivityDetalleNotaBinding
    private var notaId: Long? = null
    private var notaExistente: Nota? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleNotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notaId = intent.getLongExtra("id", -1).takeIf { it != -1L }
        notaExistente = notaId?.let { NotasManager.obtenerNota(it) }

        notaExistente?.let{
        binding.etTitulo.setText(it.titulo)
        binding.etContenido.setText(it.contenido)
        }
        binding.btnGuardar.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val contenido = binding.etContenido.text.toString()

            if (notaExistente != null) {
                NotasManager.actualizarNota(notaExistente!!.id, titulo, contenido)
            } else {
                val fecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()))
                val nuevaNota = Nota(titulo = titulo, contenido = contenido, fechaCreacion = fecha)
                NotasManager.agregarNota(nuevaNota)
            }
            finish()
        }
        binding.btnEliminar.setOnClickListener {
            notaId?.let{
                NotasManager.eliminarNota(it)
                finish()
            } ?: Toast.makeText(this, "No se pudo eliminar la nota", Toast.LENGTH_SHORT).show()
        }
    }
}