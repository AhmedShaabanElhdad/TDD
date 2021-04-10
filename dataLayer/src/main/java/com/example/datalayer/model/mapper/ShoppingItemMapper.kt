package com.example.datalayer.model.mapper

import com.example.datalayer.model.entity.ImageDTO
import com.example.datalayer.model.entity.ShoppingItemDTO
import com.example.domain.core.DomainMapper
import com.example.domain.model.MyImage
import com.example.domain.model.ShoppingItem
import javax.inject.Inject

class ShoppingItemMapper @Inject constructor() : DomainMapper<ShoppingItem, ShoppingItemDTO> {

    override fun mapToDomainModel(domain: ShoppingItemDTO): ShoppingItem {
        return ShoppingItem(
            id = domain.id ?: 0,
            name = domain.name ?: "",
            amount = domain.amount ?: 0,
            imageUrl = domain.imageUrl ?: "",
            price = domain.price ?: 0f
        )
    }

    fun toDomainList(initial: List<ShoppingItemDTO>): List<ShoppingItem> {
        return initial.map { mapToDomainModel(it) }
    }


    override fun mapFromDomainModel(model: ShoppingItem): ShoppingItemDTO {
        return ShoppingItemDTO(
            id = model.id ?: 0,
            name = model.name ?: "",
            amount = model.amount ?: 0,
            imageUrl = model.imageUrl ?: "",
            price = model.price ?: 0f
        )
    }


    fun fromDomainList(initial: List<ShoppingItem>): List<ShoppingItemDTO> {
        return initial.map { mapFromDomainModel(it) }
    }
}