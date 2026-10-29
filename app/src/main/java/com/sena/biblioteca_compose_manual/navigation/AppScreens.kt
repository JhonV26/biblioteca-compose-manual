package com.sena.biblioteca_compose_manual.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class AppScreens(val route: String) {
    // Pantallas principales
    object InicioBiblioteca : AppScreens("inicio_biblioteca")
    object ListaLibros : AppScreens("lista_libros")
    object MisReservas : AppScreens("mis_reservas")

    // Flujo 1: Mangas y Reserva
    object DetalleLibro : AppScreens("detalle_libro/{nombreLibro}") {
        fun createRoute(nombreLibro: String): String {
            val encoded = URLEncoder.encode(nombreLibro, StandardCharsets.UTF_8.toString())
            return "detalle_libro/$encoded"
        }
    }

    object ConfirmacionReserva : AppScreens("confirmacion_reserva/{nombreLibro}/{fechaReserva}") {
        fun createRoute(nombreLibro: String, fechaReserva: String): String {
            val encodedLibro = URLEncoder.encode(nombreLibro, StandardCharsets.UTF_8.toString())
            val encodedFecha = URLEncoder.encode(fechaReserva, StandardCharsets.UTF_8.toString())
            return "confirmacion_reserva/$encodedLibro/$encodedFecha"
        }
    }

    // Flujo 2: Gestión y Renovación de Reservas
    object DetalleReserva : AppScreens("detalle_reserva/{nombreLibro}/{fechaReserva}/{codigoReserva}") {
        fun createRoute(nombreLibro: String, fechaReserva: String, codigoReserva: String): String {
            val encodedLibro = URLEncoder.encode(nombreLibro, StandardCharsets.UTF_8.toString())
            val encodedFecha = URLEncoder.encode(fechaReserva, StandardCharsets.UTF_8.toString())
            val encodedCodigo = URLEncoder.encode(codigoReserva, StandardCharsets.UTF_8.toString())
            return "detalle_reserva/$encodedLibro/$encodedFecha/$encodedCodigo"
        }
    }

    object RenovarReserva : AppScreens("renovar_reserva/{nombreLibro}/{codigoReserva}") {
        fun createRoute(nombreLibro: String, codigoReserva: String): String {
            val encodedLibro = URLEncoder.encode(nombreLibro, StandardCharsets.UTF_8.toString())
            val encodedCodigo = URLEncoder.encode(codigoReserva, StandardCharsets.UTF_8.toString())
            return "renovar_reserva/$encodedLibro/$encodedCodigo"
        }
    }

    object RenovacionExitosa : AppScreens("renovacion_exitosa/{nombreLibro}/{fechaNueva}") {
        fun createRoute(nombreLibro: String, fechaNueva: String): String {
            val encodedLibro = URLEncoder.encode(nombreLibro, StandardCharsets.UTF_8.toString())
            val encodedFecha = URLEncoder.encode(fechaNueva, StandardCharsets.UTF_8.toString())
            return "renovacion_exitosa/$encodedLibro/$encodedFecha"
        }
    }
}