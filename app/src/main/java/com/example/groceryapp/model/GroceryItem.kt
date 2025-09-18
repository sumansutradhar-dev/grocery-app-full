package com.example.groceryapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class GroceryItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val listId: Long,
    val name: String,
    val quantity: Double = 1.0,
    val unit: String = "pcs",
    val pricePerUnit: Double? = null,
    val estimatedPrice: Double? = null,
    val bought: Boolean = false,
    val expiryTimestamp: Long? = null
)

