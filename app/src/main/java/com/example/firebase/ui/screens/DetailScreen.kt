package com.example.firebase.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, name: String, price: String) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Información del Producto") }) }
    ) { padding ->
        Card(
            modifier = Modifier.padding(padding).padding(16.dp).fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = name, style = MaterialTheme.typography.headlineLarge)
                Text(text = "Precio actual: S/ $price", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Volver al Listado")
                }
            }
        }
    }
}
