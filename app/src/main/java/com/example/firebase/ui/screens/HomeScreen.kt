package com.example.firebase.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.firebase.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: ProductViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Inventario Firebase") }) },
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
                    supportingContent = { Text("S/ ${product.price}") },
                    modifier = Modifier.clickable {
                        navController.navigate("detail/${product.name}/${product.price}")
                    }
                )
                HorizontalDivider()
            }
        }
    }
}
