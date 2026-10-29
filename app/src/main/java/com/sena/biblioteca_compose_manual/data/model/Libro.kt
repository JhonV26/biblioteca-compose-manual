package com.sena.biblioteca_compose_manual.data.model

data class Libro(
    val id: Int,
    val titulo: String,
    val autor: String,
    val categoria: String,
    val disponible: Boolean = true
)