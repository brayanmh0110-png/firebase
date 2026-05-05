package com.example.firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.firebase.ui.navigation.AppNavigation
import com.example.firebase.ui.theme.FirebaseTheme
import com.example.firebase.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    
    // Inyección del ViewModel refactorizado
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseTheme {
                val navController = rememberNavController()
                
                // Navegación principal con los nuevos paquetes
                AppNavigation(navController = navController, viewModel = viewModel)
            }
        }
    }
}
