package com.example.datalayer.model.mapper

import com.example.datalayer.model.entity.ImageDTO
import com.example.domain.core.DomainMapper
import com.example.domain.model.MyImage
import javax.inject.Inject

class ImageMapper @Inject constructor() : DomainMapper<MyImage, ImageDTO> {

    override fun mapToDomainModel(domain: ImageDTO): MyImage {
        return MyImage(
            id = domain.id ?: 0,
            imageURL = domain.imageURL?:"",
            pageURL = domain.pageURL?:"",
            previewURL = domain.previewURL?:""
        )
    }

    fun toDomainList(initial: List<ImageDTO>): List<MyImage> {
        return initial.map { mapToDomainModel(it) }
    }


    override fun mapFromDomainModel(model: MyImage): ImageDTO {
        TODO("Not yet implemented")
    }


    fun fromDomainList(initial: List<MyImage>): List<ImageDTO> {
        TODO("Not yet implemented")
        return listOf()
    }
}