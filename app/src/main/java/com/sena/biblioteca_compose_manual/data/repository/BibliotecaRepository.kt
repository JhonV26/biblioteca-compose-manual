package com.sena.biblioteca_compose_manual.data.repository

import com.sena.biblioteca_compose_manual.data.model.Libro
import com.sena.biblioteca_compose_manual.data.model.Reserva

object BibliotecaRepository {

    // Catálogo de Mangas y Novelas Ligeras
    val listaLibros: List<Libro> = listOf(
        Libro(1, "Shingeki no Kyojin", "Hajime Isayama", "Fantasía Oscura"),
        Libro(2, "Death Note", "Tsugumi Ohba", "Misterio / Sobrenatural"),
        Libro(3, "One Piece", "Eiichiro Oda", "Aventura / Shonen"),
        Libro(4, "Jujutsu Kaisen", "Gege Akutami", "Acción / Sobrenatural"),
        Libro(5, "Solo Leveling", "Chugong", "Fantasía / Acción"),
        Libro(6, "Kimetsu no Yaiba", "Koyoharu Gotouge", "Histórico / Shonen")
    )

    // Reservas iniciales de ejemplo
    val listaReservas: MutableList<Reserva> = mutableListOf(
        Reserva("ANI-101", "Shingeki no Kyojin", "28/10/2026", "Activa"),
        Reserva("ANI-102", "Death Note", "30/10/2026", "Activa")
    )

    // Buscar tomo por título (usado en la pantalla de detalle)
    fun obtenerLibroPorNombre(nombre: String): Libro? {
        return listaLibros.find { it.titulo.equals(nombre, ignoreCase = true) }
    }

    // Agregar nueva reserva de préstamo
    fun agregarReserva(nombreLibro: String, fechaReserva: String): Reserva {
        val nuevoCodigo = "ANI-${(100..999).random()}"
        val nuevaReserva = Reserva(nuevoCodigo, nombreLibro, fechaReserva, "Activa")
        listaReservas.add(nuevaReserva)
        return nuevaReserva
    }

    // Renovar fecha de devolución
    fun renovarReserva(codigo: String, nuevaFecha: String): Boolean {
        val index = listaReservas.indexOfFirst { it.codigoReserva == codigo }
        if (index != -1) {
            listaReservas[index] = listaReservas[index].copy(fechaReserva = nuevaFecha)
            return true
        }
        return false
    }

    // Cancelar reserva
    fun cancelarReserva(codigo: String): Boolean {
        return listaReservas.removeIf { it.codigoReserva == codigo }
    }
}