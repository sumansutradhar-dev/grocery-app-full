package com.example.groceryapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.groceryapp.viewmodel.GroceryViewModel

@Composable
fun CheckoutScreen(listId: Long, vm: GroceryViewModel, onBack: () -> Unit) {
    var total by remember { mutableStateOf(0.0) }
    LaunchedEffect(listId) { vm.total(listId) { total = it } }

    Column {
        Text("Bill for list: $listId")
        Spacer(Modifier.height(8.dp))
        Text("Total: ₹%.2f".format(total))
        Spacer(Modifier.height(12.dp))
        Button(onClick = onBack) { Text("Back") }
    }
}

