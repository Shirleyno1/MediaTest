package com.shirley.videocatalogue.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.shirley.videocatalogue.BuildConfig
import com.shirley.videocatalogue.data.Category
import com.shirley.videocatalogue.data.CategoryDao
import com.shirley.videocatalogue.data.ListConverter

@Database(entities = [Category::class], version = BuildConfig.VERSION_CODE, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
}