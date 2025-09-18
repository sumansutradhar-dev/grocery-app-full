package com.example.groceryapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.groceryapp.model.GroceryList
import com.example.groceryapp.viewmodel.GroceryViewModel

@Composable
fun MainScreen(nav: NavController, vm: GroceryViewModel) {
    val lists by vm.lists.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Grocery Lists") }) },
        floatingActionButton = { FloatingActionButton(onClick = { showDialog = true }) { Text("+") } }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (lists.isEmpty()) {
                Text("No lists yet. Tap + to create.", modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                    items(lists) { list ->
                        Card(modifier = Modifier.fillMaxWidth().padding(6.dp)) {
                            Row(modifier = Modifier.fillMaxWidth().padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(list.title)
                                Row {
                                    Text("Open", modifier = Modifier.clickable {
                                        nav.navigate("list/${list.id}/${list.title}")
                                    }.padding(6.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Delete", modifier = Modifier.clickable {
                                        vm.deleteList(list)
                                    }.padding(6.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AddListDialog(onDismiss = { showDialog = false }, onCreate = { title ->
            vm.createList(title); showDialog = false
        })
    }
}

