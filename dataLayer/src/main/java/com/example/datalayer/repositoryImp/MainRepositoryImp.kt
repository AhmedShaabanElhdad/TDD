package com.example.datalayer.repositoryImp

import com.example.datalayer.common.Resource
import com.example.datalayer.local.ShoppingItemDatabase
import com.example.datalayer.local.dao.ShoppingItemDao
import com.example.datalayer.model.entity.ShoppingItemDTO
import com.example.datalayer.model.mapper.ImageMapper
import com.example.datalayer.model.mapper.ShoppingItemMapper
import com.example.datalayer.remote.service.ApiService
import com.example.domain.model.MyImage
import com.example.domain.model.ShoppingItem
import com.example.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(
    val apiService: ApiService,
    val dao: ShoppingItemDao,
    val shoppingMapper: ShoppingItemMapper,
    val imageMapper: ImageMapper
) : MainRepository {
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

    override suspend fun search(query: String): Resource<List<MyImage>> {
        return try {
            val response = apiService.getPhoto(query)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(imageMapper.toDomainList(it.hits))
                } ?: Resource.error("error when fitch data", null)
            } else
                Resource.error("error when fitch data", null)
        } catch (e: Exception) {
            Resource.error(e.message.toString(), null)
        }
    }
}