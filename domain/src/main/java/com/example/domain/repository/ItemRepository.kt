package com.example.domain.repository

import com.example.domain.model.ShoppingItem

interface ItemRepository {

    suspend fun addITem(shoppingItem: ShoppingItem)

    suspend fun deleteITem(shoppingItem: ShoppingItem)

    suspend fun getAllItem():List<ShoppingItem>

    suspend fun calculatePrice():Float
}