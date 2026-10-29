package com.sena.biblioteca_compose_manual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sena.biblioteca_compose_manual.navigation.AppNavigation
import com.sena.biblioteca_compose_manual.ui.theme.BibliotecacomposemanualTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BibliotecacomposemanualTheme {
                AppNavigation()
            }
        }
    }
}