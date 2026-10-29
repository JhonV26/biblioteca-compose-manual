package com.sena.biblioteca_compose_manual.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sena.biblioteca_compose_manual.R
import com.sena.biblioteca_compose_manual.navigation.AppScreens
import com.sena.biblioteca_compose_manual.ui.section.ItemMangaSection

data class Manga(
    val titulo: String,
    val autor: String,
    val categoria: String,
    val imagenRes: Int
)

val listaMangas = listOf(
    Manga("Shingeki no Kyojin", "Hajime Isayama", "Fantasía Oscura", R.drawable.shingeki_no_kyojin),
    Manga("Death Note", "Tsugumi Ohba", "Misterio / Sobrenatural", R.drawable.death_note),
    Manga("One Piece", "Eiichiro Oda", "Aventura / Shonen", R.drawable.one_piece),
    Manga("Jujutsu Kaisen", "Gege Akutami", "Acción / Sobrenatural", R.drawable.jujutsu_kaisen),
    Manga("Solo Leveling", "Chugong", "Fantasía / Acción", R.drawable.solo_leveling)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaLibrosScreen(navController: NavController) {
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
            items(listaMangas) { manga ->
                ItemMangaSection(
                    manga = manga,
                    onClick = {
                        navController.navigate(AppScreens.DetalleLibro.createRoute(manga.titulo))
                    }
                )
            }
        }
    }
}
