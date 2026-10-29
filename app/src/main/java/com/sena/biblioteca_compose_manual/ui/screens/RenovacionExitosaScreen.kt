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
fun RenovacionExitosaScreen(
    navController: NavController,
    nombreLibro: String,
    fechaNueva: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Renovación Exitosa", fontWeight = FontWeight.Bold) },
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
            // Icono de confirmación verde
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Éxito",
                modifier = Modifier.size(90.dp),
                tint = Color(0xFF2E7D32)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¡Renovación Aprobada!",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tu fecha de entrega ha sido extendida en el sistema.",
                color = Color.Gray,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Comprobante de renovación
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Comprobante de Renovación",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Divider(modifier = Modifier.padding(vertical = 12.dp))
                    Text(text = "Manga: $nombreLibro", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Nueva fecha límite: $fechaNueva", fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Estado: Plazo Extendido", fontSize = 15.sp, color = Color(0xFF2E7D32))
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Botón 1: Volver a Mis Reservas limpiando pantallas intermedias
            Button(
                onClick = {
                    navController.navigate(AppScreens.MisReservas.route) {
                        popUpTo(AppScreens.MisReservas.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.ListAlt, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Volver a Mis Reservas", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón 2: Ir a la Portada Principal
            OutlinedButton(
                onClick = {
                    navController.navigate(AppScreens.InicioBiblioteca.route) {
                        popUpTo(AppScreens.InicioBiblioteca.route) { inclusive = false }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Home, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Ir al Inicio", fontSize = 16.sp)
            }
        }
    }
}