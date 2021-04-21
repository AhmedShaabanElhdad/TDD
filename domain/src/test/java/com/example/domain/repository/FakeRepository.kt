package com.example.domain.repository

import com.example.datalayer.common.Resource
import com.example.domain.model.MyImage
import com.example.domain.model.ShoppingItem

class FakeRepository : ItemRepository {

    private val shoppingItem = ShoppingItem()
    private val shoppingItems = mutableListOf<ShoppingItem>(shoppingItem)
    private var price: Float = 0f
    private var showReturnNetworkError = false

    fun changeErrorStatus(showError: Boolean) {
        showReturnNetworkError = showError
    }

    override suspend fun addITem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
    }

    override suspend fun deleteITem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
    }

    override suspend fun getAllItem(): List<ShoppingItem> {
        return shoppingItems
    }

    override suspend fun calculatePrice(): Float {
        price = shoppingItems.sumByDouble { (it.price * it.amount).toDouble() }.toFloat()
        return price
    }

    override suspend fun search(query: String): Resource<List<MyImage>> {
        return if (showReturnNetworkError)
            Resource.error("error", null)
        else
            Resource.success(listOf())
    }


}