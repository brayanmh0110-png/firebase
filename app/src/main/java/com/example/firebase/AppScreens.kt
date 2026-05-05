package com.example.firebase

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// --- NAVEGACIÓN ---
@Composable
fun AppNavigation(navController: NavHostController, viewModel: ProductViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, viewModel) }
        composable("form") { FormScreen(navController, viewModel) }
        composable("detail/{name}/{price}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            DetailScreen(navController, name, price)
        }
    }
}

// --- PANTALLA HOME ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: ProductViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Lista de Productos") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("form") }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).fillMaxSize()) {
            items(viewModel.products) { product ->
                ListItem(
                    headlineContent = { Text(product.name) },
                    supportingContent = { Text("Precio: ${product.price}") },
                    modifier = Modifier.clickable {
                        navController.navigate("detail/${product.name}/${product.price}")
                    }
                )
                HorizontalDivider()
            }
        }
    }
}

// --- PANTALLA FORMULARIO ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(navController: NavHostController, viewModel: ProductViewModel) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Nuevo Producto") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize()) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Precio") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.saveProduct(name, price) {
                        navController.popBackStack() // Volver al home al terminar
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar en Firebase")
            }
        }
    }
}

// --- PANTALLA DETALLE ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, name: String, price: String) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Detalle") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text(text = "Nombre: $name", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Precio: $price", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
    }
}
