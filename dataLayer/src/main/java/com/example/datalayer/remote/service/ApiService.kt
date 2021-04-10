package com.example.datalayer.remote.service

import com.example.datalayer.BuildConfig
import com.example.datalayer.model.response.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("")
    suspend fun getPhoto(
        @Query("q") search_key: String,
        @Query("key") api_key: String = BuildConfig.API_KEY
    ):Response<ImageResponse>

}