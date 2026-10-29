package com.sena.biblioteca_compose_manual.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sena.biblioteca_compose_manual.ui.screens.ConfirmacionReservaScreen
import com.sena.biblioteca_compose_manual.ui.screens.DetalleLibroScreen
import com.sena.biblioteca_compose_manual.ui.screens.DetalleReservaScreen
import com.sena.biblioteca_compose_manual.ui.screens.InicioScreen
import com.sena.biblioteca_compose_manual.ui.screens.ListaLibrosScreen
import com.sena.biblioteca_compose_manual.ui.screens.MisReservasScreen
import com.sena.biblioteca_compose_manual.ui.screens.RenovacionExitosaScreen
import com.sena.biblioteca_compose_manual.ui.screens.RenovarReservaScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.InicioBiblioteca.route
    ) {
        // ==========================================
        // FLUJO 1: CATÁLOGO Y RESERVA DE MANGAS
        // ==========================================

        // 1. Portada de Inicio
        composable(route = AppScreens.InicioBiblioteca.route) {
            InicioScreen(navController = navController)
        }

        // 2. Catálogo con LazyColumn
        composable(route = AppScreens.ListaLibros.route) {
            ListaLibrosScreen(navController = navController)
        }

        // 3. Ficha Técnica del Manga (1 Parámetro: nombreLibro)
        composable(
            route = AppScreens.DetalleLibro.route,
            arguments = listOf(
                navArgument("nombreLibro") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val rawNombre = backStackEntry.arguments?.getString("nombreLibro") ?: ""
            val nombreLibro = URLDecoder.decode(rawNombre, StandardCharsets.UTF_8.toString())
            DetalleLibroScreen(navController = navController, nombreLibro = nombreLibro)
        }

        // 4. Ticket de Préstamo Exitoso (2 Parámetros: nombreLibro + fechaReserva)
        composable(
            route = AppScreens.ConfirmacionReserva.route,
            arguments = listOf(
                navArgument("nombreLibro") { type = NavType.StringType },
                navArgument("fechaReserva") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val rawNombre = backStackEntry.arguments?.getString("nombreLibro") ?: ""
            val nombreLibro = URLDecoder.decode(rawNombre, StandardCharsets.UTF_8.toString())
            val rawFecha = backStackEntry.arguments?.getString("fechaReserva") ?: ""
            val fechaReserva = URLDecoder.decode(rawFecha, StandardCharsets.UTF_8.toString())

            ConfirmacionReservaScreen(
                navController = navController,
                nombreLibro = nombreLibro,
                fechaReserva = fechaReserva
            )
        }

        // ==========================================
        // FLUJO 2: GESTIÓN Y RENOVACIÓN DE RESERVAS
        // ==========================================

        // 5. Lista de Reservas Activas
        composable(route = AppScreens.MisReservas.route) {
            MisReservasScreen(navController = navController)
        }

        // 6. Gestión de Reserva (3 Parámetros: nombreLibro + fechaReserva + codigoReserva)
        composable(
            route = AppScreens.DetalleReserva.route,
            arguments = listOf(
                navArgument("nombreLibro") { type = NavType.StringType },
                navArgument("fechaReserva") { type = NavType.StringType },
                navArgument("codigoReserva") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val rawNombre = backStackEntry.arguments?.getString("nombreLibro") ?: ""
            val nombreLibro = URLDecoder.decode(rawNombre, StandardCharsets.UTF_8.toString())
            val rawFecha = backStackEntry.arguments?.getString("fechaReserva") ?: ""
            val fechaReserva = URLDecoder.decode(rawFecha, StandardCharsets.UTF_8.toString())
            val rawCodigo = backStackEntry.arguments?.getString("codigoReserva") ?: ""
            val codigoReserva = URLDecoder.decode(rawCodigo, StandardCharsets.UTF_8.toString())

            DetalleReservaScreen(
                navController = navController,
                nombreLibro = nombreLibro,
                fechaReserva = fechaReserva,
                codigoReserva = codigoReserva
            )
        }

        // 7. Formulario de Renovación (2 Parámetros: nombreLibro + codigoReserva)
        composable(
            route = AppScreens.RenovarReserva.route,
            arguments = listOf(
                navArgument("nombreLibro") { type = NavType.StringType },
                navArgument("codigoReserva") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val rawNombre = backStackEntry.arguments?.getString("nombreLibro") ?: ""
            val nombreLibro = URLDecoder.decode(rawNombre, StandardCharsets.UTF_8.toString())
            val rawCodigo = backStackEntry.arguments?.getString("codigoReserva") ?: ""
            val codigoReserva = URLDecoder.decode(rawCodigo, StandardCharsets.UTF_8.toString())

            RenovarReservaScreen(
                navController = navController,
                nombreLibro = nombreLibro,
                codigoReserva = codigoReserva
            )
        }

        // 8. Ticket de Renovación Exitosa (2 Parámetros: nombreLibro + fechaNueva)
        composable(
            route = AppScreens.RenovacionExitosa.route,
            arguments = listOf(
                navArgument("nombreLibro") { type = NavType.StringType },
                navArgument("fechaNueva") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val rawNombre = backStackEntry.arguments?.getString("nombreLibro") ?: ""
            val nombreLibro = URLDecoder.decode(rawNombre, StandardCharsets.UTF_8.toString())
            val rawFecha = backStackEntry.arguments?.getString("fechaNueva") ?: ""
            val fechaNueva = URLDecoder.decode(rawFecha, StandardCharsets.UTF_8.toString())

            RenovacionExitosaScreen(
                navController = navController,
                nombreLibro = nombreLibro,
                fechaNueva = fechaNueva
            )
        }
    }
}