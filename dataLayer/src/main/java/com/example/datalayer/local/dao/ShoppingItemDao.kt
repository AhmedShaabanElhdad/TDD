package com.example.datalayer.local.dao

import androidx.room.*
import com.example.datalayer.model.entity.ShoppingItem

@Dao
abstract class ShoppingItemDao: BaseDao<ShoppingItem>() {

    suspend fun addItem(shoppingItem: ShoppingItem){
        insert(shoppingItem)
    }
    suspend fun addItems(items: List<ShoppingItem>){
        insert(items)
    }

    suspend fun deleteItem(shoppingItem: ShoppingItem){
        delete(shoppingItem)
    }

    @Query("SELECT * FROM ShoppingItem")
     abstract suspend fun getAllItem():List<ShoppingItem>

    @Query("SELECT SUM(amount * price) FROM ShoppingItem")
    abstract suspend fun getTotalPrice():Float

}