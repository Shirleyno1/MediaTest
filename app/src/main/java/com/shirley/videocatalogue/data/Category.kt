package com.shirley.videocatalogue.data

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "category")
data class Category(@PrimaryKey val category: String = "",
                    @Embedded
                    val items: List<VideoItem>?)