package com.example.firebase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebase.ui.screens.DetailScreen
import com.example.firebase.ui.screens.FormScreen
import com.example.firebase.ui.screens.HomeScreen
import com.example.firebase.viewmodel.ProductViewModel

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
