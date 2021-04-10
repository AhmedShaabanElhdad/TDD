package com.example.domain.repository

import com.example.datalayer.common.Resource
import com.example.domain.model.MyImage
import com.example.domain.model.ShoppingItem

interface MainRepository {

    suspend fun addITem(shoppingItem: ShoppingItem)

    suspend fun deleteITem(shoppingItem: ShoppingItem)

    suspend fun getAllItem():List<ShoppingItem>

    suspend fun calculatePrice():Float

    suspend fun search(query:String):Resource<List<MyImage>>
}