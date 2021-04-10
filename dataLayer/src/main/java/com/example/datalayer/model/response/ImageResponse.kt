package com.example.datalayer.model.response

import com.example.datalayer.model.entity.ImageDTO

data class ImageResponse(
    val hits: List<ImageDTO>,
    val total: Int,
    val totalHits: Int

)

fun ImageResponse.toDomainModel(){
}