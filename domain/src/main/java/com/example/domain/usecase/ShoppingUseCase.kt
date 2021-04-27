package com.example.domain.usecase

import com.example.domain.model.ShoppingItem
import com.example.domain.repository.ImageRepository
import com.example.domain.repository.ItemRepository
import javax.inject.Inject

class ShoppingUseCase @Inject constructor(val repository: ItemRepository,val imageRepository: ImageRepository){

    suspend fun getAllItems() = repository.getAllItem()

    suspend fun calculatePrice() = repository.calculatePrice()


    suspend fun addItem(shoppingItem: ShoppingItem) =
        repository.addITem(shoppingItem)


    suspend fun deleteItem(shoppingItem: ShoppingItem) =
        repository.deleteITem(shoppingItem)

}