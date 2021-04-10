package com.example.datalayer.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datalayer.local.dao.ShoppingItemDao
import com.example.datalayer.model.entity.ShoppingItemDTO

@Database(entities = [ShoppingItemDTO::class], version = 1)
abstract class ShoppingItemDatabase : RoomDatabase() {
    abstract fun shoppingItemDao(): ShoppingItemDao
}