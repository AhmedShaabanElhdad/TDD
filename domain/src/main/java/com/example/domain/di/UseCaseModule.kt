package com.example.domain.di

import com.example.domain.repository.ImageRepository
import com.example.domain.repository.ItemRepository
import com.example.domain.usecase.ShoppingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideShoppingUseCase(imageRepository: ImageRepository,itemRepository: ItemRepository) =
        ShoppingUseCase(itemRepository,imageRepository)


}