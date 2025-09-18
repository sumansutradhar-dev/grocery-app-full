package com.example.groceryapp.ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.window.DialogProperties

@Composable
fun AddItemDialog(onDismiss: () -> Unit, onAdd: (String, Double, String, Double?) -> Unit) {
    var name by remember { mutableStateOf("") }
    var qty by remember { mutableStateOf("1") }
    var unit by remember { mutableStateOf("pcs") }
    var price by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Item") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                TextField(value = qty, onValueChange = { qty = it }, label = { Text("Quantity") })
                TextField(value = unit, onValueChange = { unit = it }, label = { Text("Unit") })
                TextField(value = price, onValueChange = { price = it }, label = { Text("Price per unit (optional)") })
            }
        },
        confirmButton = { Button(onClick = { if (name.isNotBlank()) onAdd(name, qty.toDoubleOrNull() ?: 1.0, unit, price.toDoubleOrNull()) }) { Text("Add") } },
        dismissButton = { OutlinedButton(onClick = onDismiss) { Text("Cancel") } },
        properties = DialogProperties()
    )
}

