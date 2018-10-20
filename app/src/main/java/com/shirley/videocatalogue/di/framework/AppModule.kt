package com.shirley.videocatalogue.di.framework

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val app:Application) {

    @Provides
    @Singleton
    fun getContext(): Context = app
}