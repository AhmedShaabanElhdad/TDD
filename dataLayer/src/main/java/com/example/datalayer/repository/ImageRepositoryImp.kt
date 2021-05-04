package com.example.datalayer.repository

import com.example.datalayer.model.mapper.ImageMapper
import com.example.datalayer.remote.service.ApiService
import com.example.domain.core.Resource
import com.example.domain.model.MyImage
import com.example.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImp @Inject constructor(
    val apiService: ApiService,
    val imageMapper: ImageMapper
) : ImageRepository {

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