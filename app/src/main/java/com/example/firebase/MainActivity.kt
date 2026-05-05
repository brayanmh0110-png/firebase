package com.example.firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.firebase.ui.theme.FirebaseTheme

class MainActivity : ComponentActivity() {
    
    // Inicializamos el ViewModel
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseTheme {
                // Controlador de navegación
                val navController = rememberNavController()
                
                // Llamamos a la navegación principal
                AppNavigation(navController = navController, viewModel = viewModel)
            }
        }
    }
}
