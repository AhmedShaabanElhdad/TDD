package com.example.datalayer.local.dao

import androidx.room.*
import com.example.datalayer.model.entity.ShoppingItemDTO

@Dao
abstract class ShoppingItemDao: BaseDao<ShoppingItemDTO>() {

    suspend fun addItem(shoppingItem: ShoppingItemDTO){
        insert(shoppingItem)
    }
    suspend fun addItems(items: List<ShoppingItemDTO>){
        insert(items)
    }

    suspend fun deleteItem(shoppingItem: ShoppingItemDTO){
        delete(shoppingItem)
    }

    @Query("SELECT * FROM ShoppingItem")
     abstract suspend fun getAllItem():List<ShoppingItemDTO>

    @Query("SELECT SUM(amount * price) FROM ShoppingItem")
    abstract suspend fun getTotalPrice():Float

}