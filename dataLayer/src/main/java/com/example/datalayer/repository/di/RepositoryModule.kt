package com.example.datalayer.repository.di

import com.example.datalayer.local.dao.ShoppingItemDao
import com.example.datalayer.local.di.DatabaseModule
import com.example.datalayer.model.mapper.ImageMapper
import com.example.datalayer.model.mapper.ShoppingItemMapper
import com.example.datalayer.remote.di.NetworkModule
import com.example.datalayer.remote.service.ApiService
import com.example.datalayer.repository.ImageRepositoryImp
import com.example.datalayer.repository.ItemRepositoryImp
import com.example.domain.repository.ImageRepository
import com.example.domain.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideShoppingItemRepository(
        shoppingItemDao: ShoppingItemDao,
        shoppingItemMapper: ShoppingItemMapper
    ) = ItemRepositoryImp(shoppingItemDao, shoppingItemMapper) as ItemRepository


    @Singleton
    @Provides
    fun provideImageRepository(
        apiService: ApiService,
        imageMapper: ImageMapper
    ) = ImageRepositoryImp(apiService, imageMapper) as ImageRepository


}