package com.example.domain.repository

import com.example.domain.core.Resource
import com.example.domain.model.MyImage

interface ImageRepository {

    suspend fun search(query:String): Resource<List<MyImage>>

}