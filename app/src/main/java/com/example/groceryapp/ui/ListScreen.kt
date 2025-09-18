package com.example.groceryapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.groceryapp.model.GroceryItem
import com.example.groceryapp.viewmodel.GroceryViewModel

@Composable
fun ListScreen(listId: Long, title: String, vm: GroceryViewModel, onBack: () -> Unit) {
    var showAdd by remember { mutableStateOf(false) }
    val items by vm.items.collectAsState()

    LaunchedEffect(listId) { vm.loadItems(listId) }

    Scaffold(topBar = {
        TopAppBar(title = { Text(title) }, navigationIcon = null)
    }, floatingActionButton = {
        FloatingActionButton(onClick = { showAdd = true }) { Text("+") }
    }) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (items.isEmpty()) {
                Text("No items. Add using +", modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(items) { it ->
                        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text(it.name)
                                Text("${it.quantity} ${it.unit} • ${it.pricePerUnit ?: it.estimatedPrice ?: 0.0}")
                            }
                            Row {
                                Text(if (it.bought) "Bought" else "Mark", modifier = Modifier.clickable {
                                    vm.toggleBought(it, listId)
                                }.padding(6.dp))
                            }
                        }
                        Divider()
                    }
                }
            }

            Spacer(Modifier.height(8.dp))
            Button(onClick = { onBack(); /* navigate to checkout would be from main - using back for simplicity */ }) {
                Text("Back")
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = { /* open checkout */ }) {
                Text("Checkout")
            }
        }
    }

    if (showAdd) {
        AddItemDialog(onDismiss = { showAdd = false }, onAdd = { name, qty, unit, price ->
            vm.addItem(GroceryItem(listId = listId, name = name, quantity = qty, unit = unit, pricePerUnit = price), listId)
            showAdd = false
        })
    }
}

