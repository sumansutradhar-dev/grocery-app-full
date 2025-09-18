package com.example.groceryapp.ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.*
import androidx.compose.ui.window.DialogProperties

@Composable
fun AddListDialog(onDismiss: () -> Unit, onCreate: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Create list") },
        text = { TextField(value = text, onValueChange = { text = it }, label = { Text("Title") }) },
        confirmButton = { Button(onClick = { if (text.isNotBlank()) onCreate(text) }) { Text("Create") } },
        dismissButton = { OutlinedButton(onClick = onDismiss) { Text("Cancel") } },
        properties = DialogProperties()
    )
}

