package com.sakhura.notasapp2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakhura.notasapp2.model.Nota

class NotasAdapter (
    private val notas: List<Nota>,
    private val onClick: (nota) -> Unit
) : RecyclerView.Adapter<NotasAdapter.NotaViewHolder>(){
    inner class NotaViewHolder(val binding: ItemNotaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val binding = ItemNotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotaViewHolder(binding)

    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.binding.tvTitulo.text = nota.titulo
        holder.binding.tvFecha.text= nota.fechaCreacion
        holder.binding.root.setOnClickListener {
            onClick(nota)
        }
    }
}