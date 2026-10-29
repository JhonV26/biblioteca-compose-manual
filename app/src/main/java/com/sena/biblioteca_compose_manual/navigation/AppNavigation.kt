package com.sena.biblioteca_compose_manual.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sena.biblioteca_compose_manual.ui.screens.ConfirmacionReservaScreen
import com.sena.biblioteca_compose_manual.ui.screens.DetalleLibroScreen
import com.sena.biblioteca_compose_manual.ui.screens.InicioScreen
import com.sena.biblioteca_compose_manual.ui.screens.ListaLibrosScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.InicioBiblioteca.route
    ) {
        // 1. Pantalla de Inicio
        composable(route = AppScreens.InicioBiblioteca.route) {
            InicioScreen(navController = navController)
        }

        // 2. Catálogo de Mangas
        composable(route = AppScreens.ListaLibros.route) {
            ListaLibrosScreen(navController = navController)
        }

        // 3. Detalle del Manga (Recibe parámetro nombreLibro)
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

        // 4. Confirmación de Reserva (Recibe nombreLibro y fechaReserva)
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

        // Destino provisional para Mis Reservas (evita cierres si el usuario hace clic antes del Flujo 2)
        composable(route = AppScreens.MisReservas.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Mis Reservas Activas (Se completará en la siguiente rama: feature/flujo-reservas)")
            }
        }
    }
}