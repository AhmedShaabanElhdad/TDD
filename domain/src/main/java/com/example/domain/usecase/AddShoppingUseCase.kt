package com.example.domain.usecase

import com.example.domain.model.ShoppingItem
import com.example.domain.repository.ItemRepository

class AddShoppingUseCase(val repository: ItemRepository) {

    suspend fun addItem(shoppingItem: ShoppingItem) =
        repository.addITem(shoppingItem)


    suspend fun deleteItem(shoppingItem: ShoppingItem) =
        repository.deleteITem(shoppingItem)
}