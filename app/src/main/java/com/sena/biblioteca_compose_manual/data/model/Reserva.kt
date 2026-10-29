package com.sena.biblioteca_compose_manual.data.model

data class Reserva(
    val codigoReserva: String,
    val nombreLibro: String,
    val fechaReserva: String,
    val estado: String = "Activa"
)