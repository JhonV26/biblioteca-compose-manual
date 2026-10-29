package com.sena.biblioteca_compose_manual.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sena.biblioteca_compose_manual.data.repository.BibliotecaRepository
import com.sena.biblioteca_compose_manual.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenovarReservaScreen(
    navController: NavController,
    nombreLibro: String,
    codigoReserva: String
) {
    // Estado para la nueva fecha de devolución
    var nuevaFecha by remember { mutableStateOf("15/11/2026") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Renovar Préstamo", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
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
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Tarjeta resumen del préstamo actual
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Autorenew,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Extensión de Préstamo",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Divider(modifier = Modifier.padding(vertical = 12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.MenuBook, contentDescription = null, tint = Color.Gray)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = nombreLibro, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "Código de reserva: $codigoReserva", color = Color.Gray, fontSize = 14.sp)
                }
            }

            Text(
                text = "Ingresa la nueva fecha límite de devolución:",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )

            // Campo de texto para la nueva fecha
            OutlinedTextField(
                value = nuevaFecha,
                onValueChange = { nuevaFecha = it },
                label = { Text("Nueva fecha (DD/MM/AAAA)") },
                leadingIcon = {
                    Icon(Icons.Default.CalendarMonth, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Text(
                text = "Recuerda que cada renovación otorga un máximo de 14 días adicionales.",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.weight(1f))

            // Botón de confirmación
            Button(
                onClick = {
                    if (nuevaFecha.isNotBlank()) {
                        // 1. Actualizamos la reserva en el Singleton del repositorio
                        BibliotecaRepository.renovarReserva(codigoReserva, nuevaFecha)

                        // 2. Navegamos a la pantalla de éxito pasando los datos
                        navController.navigate(
                            AppScreens.RenovacionExitosa.createRoute(
                                nombreLibro = nombreLibro,
                                fechaNueva = nuevaFecha
                            )
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Confirmar Nueva Fecha", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}