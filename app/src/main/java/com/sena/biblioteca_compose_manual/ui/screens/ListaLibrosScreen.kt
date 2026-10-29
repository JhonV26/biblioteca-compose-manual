package com.sena.biblioteca_compose_manual.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sena.biblioteca_compose_manual.data.model.Libro
import com.sena.biblioteca_compose_manual.data.repository.BibliotecaRepository
import com.sena.biblioteca_compose_manual.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaLibrosScreen(navController: NavController) {
    val mangas = BibliotecaRepository.listaLibros

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Mangas", fontWeight = FontWeight.Bold) },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(mangas) { manga ->
                ItemMangaCard(
                    manga = manga,
                    onClick = {
                        // Navegación con parámetro a DetalleLibro
                        navController.navigate(AppScreens.DetalleLibro.createRoute(manga.titulo))
                    }
                )
            }
        }
    }
}

@Composable
fun ItemMangaCard(
    manga: Libro,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.MenuBook,
                contentDescription = null,
                modifier = Modifier.size(36.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = manga.titulo,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Mangaka: ${manga.autor}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                SuggestionChip(
                    onClick = { },
                    label = { Text(manga.categoria, fontSize = 12.sp) }
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Ir al detalle",
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}