package com.example.domain.repository

import com.example.datalayer.common.Resource
import com.example.domain.model.MyImage
import com.example.domain.model.ShoppingItem

interface ImageRepository {

    suspend fun search(query:String):Resource<List<MyImage>>

}