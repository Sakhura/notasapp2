package com.sakhura.notasapp2.model

data class Nota(
    val id: Long = System.currentTimeMillis(),
    val titulo: String,
    val contenido: String,
    val fechaCreacion: String
   // val fechaModificacion: Long = System.currentTimeMillis()

)