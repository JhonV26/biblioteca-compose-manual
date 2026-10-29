package com.sena.biblioteca_compose_manual.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sena.biblioteca_compose_manual.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmacionReservaScreen(
    navController: NavController,
    nombreLibro: String,
    fechaReserva: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Confirmación de Préstamo", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icono de confirmación
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Éxito",
                modifier = Modifier.size(90.dp),
                tint = Color(0xFF2E7D32) // Verde éxito
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¡Tomo Reservado con Éxito!",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tu solicitud ha sido procesada por la biblioteca.",
                color = Color.Gray,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Tarjeta tipo comprobante / ticket
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Resumen del Préstamo",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Divider(modifier = Modifier.padding(vertical = 12.dp))
                    Text(text = "Manga: $nombreLibro", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Fecha de inicio: $fechaReserva", fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Estado: Préstamo Activo", fontSize = 15.sp, color = Color(0xFF2E7D32))
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Botón 1: Ir a Mis Reservas (Limpiando pantallas intermedias)
            Button(
                onClick = {
                    navController.navigate(AppScreens.MisReservas.route) {
                        popUpTo(AppScreens.InicioBiblioteca.route) { inclusive = false }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.ListAlt, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Ver Mis Reservas Activas", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón 2: Volver al Inicio
            OutlinedButton(
                onClick = {
                    navController.navigate(AppScreens.InicioBiblioteca.route) {
                        popUpTo(AppScreens.InicioBiblioteca.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Home, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Volver al Inicio", fontSize = 16.sp)
            }
        }
    }
}