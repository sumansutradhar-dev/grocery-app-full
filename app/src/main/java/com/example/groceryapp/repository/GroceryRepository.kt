package com.example.groceryapp.repository

import android.content.Context
import com.example.groceryapp.data.AppDatabase
import com.example.groceryapp.model.GroceryItem
import com.example.groceryapp.model.GroceryList

class GroceryRepository(context: Context) {
    private val dao = AppDatabase.get(context).groceryDao()

    suspend fun createList(title: String) = dao.insertList(GroceryList(title = title))
    suspend fun lists() = dao.getLists()
    suspend fun deleteList(list: GroceryList) = dao.deleteList(list)

    suspend fun addItem(item: GroceryItem) = dao.insertItem(item)
    suspend fun itemsFor(listId: Long) = dao.getItemsForList(listId)
    suspend fun updateItem(item: GroceryItem) = dao.updateItem(item)
    suspend fun total(listId: Long) = dao.totalForList(listId) ?: 0.0
}

