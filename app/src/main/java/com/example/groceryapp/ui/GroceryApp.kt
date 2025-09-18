package com.example.groceryapp.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.groceryapp.viewmodel.GroceryViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun GroceryApp() {
    val nav = rememberNavController()
    val vm: GroceryViewModel = viewModel()

    MaterialTheme {
        Surface {
            NavHost(navController = nav, startDestination = "main") {
                composable("main") {
                    MainScreen(nav, vm)
                }
                composable("list/{listId}/{title}") { backStackEntry ->
                    val listId = backStackEntry.arguments?.getString("listId")?.toLongOrNull() ?: 0L
                    val title = backStackEntry.arguments?.getString("title") ?: ""
                    ListScreen(listId = listId, title = title, vm = vm, onBack = { nav.popBackStack() })
                }
                composable("checkout/{listId}") { b ->
                    val listId = b.arguments?.getString("listId")?.toLongOrNull() ?: 0L
                    CheckoutScreen(listId = listId, vm = vm, onBack = { nav.popBackStack() })
                }
            }
        }
    }
}

