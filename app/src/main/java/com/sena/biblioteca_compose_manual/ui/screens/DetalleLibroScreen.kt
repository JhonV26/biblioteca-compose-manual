package com.sena.biblioteca_compose_manual.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sena.biblioteca_compose_manual.navigation.AppScreens
import com.sena.biblioteca_compose_manual.ui.components.MangaCover

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleLibroScreen(
    navController: NavController,
    nombreLibro: String
) {
    val manga = remember(nombreLibro) {
        listaMangas.find { it.titulo.equals(nombreLibro, ignoreCase = true) }
    }

    var fechaReserva by remember { mutableStateOf("29/10/2026") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Manga", fontWeight = FontWeight.Bold) },
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
        if (manga != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MangaCover(
                            imagenRes = manga.imagenRes,
                            contentDescription = manga.titulo,
                            ancho = 120.dp,
                            alto = 170.dp
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = manga.titulo,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Mangaka / Autor: ${manga.autor}", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Categoría: ${manga.categoria}", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = Color(0xFF2E7D32),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Disponible para préstamo inmediato",
                                color = Color(0xFF2E7D32),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Text(
                    text = "Fecha de inicio del préstamo:",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

                OutlinedTextField(
                    value = fechaReserva,
                    onValueChange = { fechaReserva = it },
                    label = { Text("Fecha de reserva (DD/MM/AAAA)") },
                    leadingIcon = {
                        Icon(Icons.Default.CalendarMonth, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        if (fechaReserva.isNotBlank()) {
                            navController.navigate(
                                AppScreens.ConfirmacionReserva.createRoute(manga.titulo, fechaReserva)
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Confirmar Préstamo de Tomo", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Manga no encontrado en el catálogo.")
            }
        }
    }
}
