package com.sakhura.notasapp2.model

data class Nota(
    val id: Long = System.currentTimeMillis(),
    var titulo: String,
    var contenido: String,
    val fechaCreacion: String
   // val fechaModificacion: Long = System.currentTimeMillis()

)