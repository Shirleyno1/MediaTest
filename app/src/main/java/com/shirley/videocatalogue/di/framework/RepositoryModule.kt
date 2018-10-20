package com.shirley.videocatalogue.di.framework

import com.shirley.videocatalogue.data.CategoryRepository
import com.shirley.videocatalogue.data.CategoryRepositoryImpl
import com.shirley.videocatalogue.data.CategoryService
import com.shirley.videocatalogue.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun getCategoryRepository(service: CategoryService, database: AppDatabase): CategoryRepository{
        return CategoryRepositoryImpl(service, database.getCategoryDao())
    }
}