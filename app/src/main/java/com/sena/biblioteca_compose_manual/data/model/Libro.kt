package com.sena.biblioteca_compose_manual.data.model

import androidx.annotation.DrawableRes

data class Libro(
    val id: Int,
    val titulo: String,
    val autor: String,
    val categoria: String,
    val disponible: Boolean = true,
    @DrawableRes val imagenRes: Int // Identificador del recurso en R.drawable
)