package com.sakhura.notasapp2.model

data class Nota(
    val id: Long = System.currentTimeMillis(),
    val titulo: String,
    val contenido: String,
    val fechaCreacion: Long = System.currentTimeMillis(),
   // val fechaModificacion: Long = System.currentTimeMillis()

)