package com.example.groceryapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.model.GroceryItem
import com.example.groceryapp.model.GroceryList
import com.example.groceryapp.repository.GroceryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = GroceryRepository(application)

    private val _lists = MutableStateFlow<List<GroceryList>>(emptyList())
    val lists: StateFlow<List<GroceryList>> = _lists

    private val _items = MutableStateFlow<List<GroceryItem>>(emptyList())
    val items: StateFlow<List<GroceryItem>> = _items

    init {
        refreshLists()
    }

    fun refreshLists() = viewModelScope.launch {
        _lists.value = repo.lists()
    }

    fun createList(title: String) = viewModelScope.launch {
        repo.createList(title)
        refreshLists()
    }

    fun deleteList(list: GroceryList) = viewModelScope.launch {
        repo.deleteList(list)
        refreshLists()
    }

    fun loadItems(listId: Long) = viewModelScope.launch {
        _items.value = repo.itemsFor(listId)
    }

    fun addItem(item: GroceryItem, listId: Long) = viewModelScope.launch {
        repo.addItem(item.copy(listId = listId))
        loadItems(listId)
    }

    fun toggleBought(item: GroceryItem, listId: Long) = viewModelScope.launch {
        repo.updateItem(item.copy(bought = !item.bought))
        loadItems(listId)
    }

    fun total(listId: Long, onResult: (Double) -> Unit) = viewModelScope.launch {
        val t = repo.total(listId)
        onResult(t)
    }
}

