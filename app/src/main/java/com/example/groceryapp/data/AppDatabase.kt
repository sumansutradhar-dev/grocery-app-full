package com.example.groceryapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.groceryapp.model.GroceryItem
import com.example.groceryapp.model.GroceryList

@Database(entities = [GroceryItem::class, GroceryList::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun groceryDao(): GroceryDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "grocery-db").build().also { instance = it }
            }
    }
}

