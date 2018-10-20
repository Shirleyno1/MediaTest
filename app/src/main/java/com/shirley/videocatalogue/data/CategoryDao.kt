package com.shirley.videocatalogue.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAllCategories(): Single<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCatogories(categories: List<Category>)

}