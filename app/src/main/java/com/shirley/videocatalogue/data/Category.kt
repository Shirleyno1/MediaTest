package com.shirley.videocatalogue.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@Entity(tableName = "category")
data class Category(@PrimaryKey
                    val category: String,
                    @TypeConverters(ListConverter::class)
                    val items: List<VideoItem>? = null): Comparable<Category> {
    override fun compareTo(other: Category): Int {
        if ("Features".equals(category)){
            return -1
        }
        return 1
    }
}

class ListConverter {
    @TypeConverter
    fun fromListToJson(from: List<VideoItem>): String {
        val type = Types.newParameterizedType(List::class.java, VideoItem::class.java)
        return Moshi.Builder().build().adapter<List<VideoItem>>(type).toJson(from)
    }

    @TypeConverter
    fun toVideoItemList(json: String): List<VideoItem> {
        val type = Types.newParameterizedType(List::class.java, VideoItem::class.java)
        return Moshi.Builder().build().adapter<List<VideoItem>>(type).fromJson(json)
    }
}