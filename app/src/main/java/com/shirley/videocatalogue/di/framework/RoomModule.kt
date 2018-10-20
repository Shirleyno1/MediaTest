package com.shirley.videocatalogue.di.framework

import android.arch.persistence.room.Room
import android.content.Context
import com.shirley.videocatalogue.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun getRoomInstance(context: Context): AppDatabase{
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "sbs_video").build()
    }
}