package com.shirley.videocatalogue.di.framework

import com.shirley.videocatalogue.data.CategoryService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ServiceModule {
    @Provides
    fun getCategoryService(retrofit: Retrofit):CategoryService{
        return retrofit.create(CategoryService::class.java)
    }
}