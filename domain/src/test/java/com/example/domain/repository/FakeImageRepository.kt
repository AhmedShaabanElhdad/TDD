package com.example.domain.repository

import com.example.domain.core.Resource
import com.example.domain.model.MyImage

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