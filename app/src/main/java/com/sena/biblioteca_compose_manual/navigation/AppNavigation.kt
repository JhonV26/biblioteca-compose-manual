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
        composable(route = AppScreens.InicioBiblioteca.route) {
            InicioScreen(navController = navController)
        }

        composable(route = AppScreens.ListaLibros.route) {
            ListaLibrosScreen(navController = navController)
        }

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

        composable(route = AppScreens.MisReservas.route) {
            MisReservasScreen(navController = navController)
        }

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
