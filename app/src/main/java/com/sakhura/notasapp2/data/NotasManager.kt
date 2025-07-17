package com.sakhura.notasapp2.data

import com.sakhura.notasapp2.model.Nota

object NotasManager {
    val listaNotas = mutableListOf<Nota>()

    fun agregarNota(nota: Nota) {
        listaNotas.add(nota)
    }

    fun actualizarNota(id: Long, nuevaTitulo: String, nuevoContenido: Nota) {
        listaNotas.find { it.id == id }?.apply {
            titulo = nuevaTitulo
            contenido = nuevoContenido
        }
    }
    fun buscarNotas(titulo: String): List<Nota> {
        return listaNotas.filter { it.titulo.contains(titulo, ignoreCase = true) }
    }

    fun obtenerNota(id: Long): Nota? {
        return listaNotas.find { it.id == id }
    }

    fun eliminarNota(id: Long) {
        return listaNotas.find { it.id == id }
    }
}