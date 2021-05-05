package com.example.datalayer.local.di

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.datalayer.BuildConfig
import com.example.datalayer.local.ShoppingItemDatabase
import com.example.datalayer.model.mapper.ImageMapper
import com.example.datalayer.model.mapper.ShoppingItemMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseTestModule {

    @Provides
    @Named("test_db")
    fun provideRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ShoppingItemDatabase::class.java, BuildConfig.DB_NAME)
            .build()
}