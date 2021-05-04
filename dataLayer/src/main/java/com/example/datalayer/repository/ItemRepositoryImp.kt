package com.example.datalayer.repository

import com.example.datalayer.local.dao.ShoppingItemDao
import com.example.datalayer.model.mapper.ShoppingItemMapper
import com.example.domain.model.ShoppingItem
import com.example.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImp @Inject constructor(
    val dao: ShoppingItemDao,
    val shoppingMapper: ShoppingItemMapper
) : ItemRepository {
    override suspend fun addITem(shoppingItem: ShoppingItem) {
        dao.addItem(shoppingMapper.mapFromDomainModel(shoppingItem))
    }

    override suspend fun deleteITem(shoppingItem: ShoppingItem) {
        dao.deleteItem(shoppingMapper.mapFromDomainModel(shoppingItem))
    }

    override suspend fun getAllItem(): List<ShoppingItem> {
        return shoppingMapper.toDomainList(dao.getAllItem())
    }

    override suspend fun calculatePrice(): Float {
        return dao.getTotalPrice()
    }

}