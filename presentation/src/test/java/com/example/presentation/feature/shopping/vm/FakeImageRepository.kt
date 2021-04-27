package com.example.presentation.feature.shopping.vm

import com.example.domain.core.Resource
import com.example.domain.model.MyImage
import com.example.domain.repository.ImageRepository

class FakeImageRepository : ImageRepository {

    private var showReturnNetworkError = false

    fun changeErrorStatus(showError: Boolean) {
        showReturnNetworkError = showError
    }


    override suspend fun search(query: String): Resource<List<MyImage>> {
        return if (showReturnNetworkError)
            Resource.error("error", null)
        else
            Resource.success(listOf())
    }


}