package com.example.groceryapp.data

import androidx.room.*
import com.example.groceryapp.model.GroceryItem
import com.example.groceryapp.model.GroceryList

@Dao
interface GroceryDao {
    // Lists
    @Insert
    suspend fun insertList(list: GroceryList): Long

    @Query("SELECT * FROM lists ORDER BY createdAt DESC")
    suspend fun getLists(): List<GroceryList>

    @Delete
    suspend fun deleteList(list: GroceryList)

    // Items
    @Insert
    suspend fun insertItem(item: GroceryItem): Long

    @Update
    suspend fun updateItem(item: GroceryItem)

    @Query("SELECT * FROM items WHERE listId = :listId")
    suspend fun getItemsForList(listId: Long): List<GroceryItem>

    @Query("SELECT SUM(CASE WHEN pricePerUnit IS NOT NULL THEN pricePerUnit * quantity WHEN estimatedPrice IS NOT NULL THEN estimatedPrice ELSE 0 END) FROM items WHERE listId = :listId")
    suspend fun totalForList(listId: Long): Double?
}

