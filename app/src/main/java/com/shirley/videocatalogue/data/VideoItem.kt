package com.shirley.videocatalogue.data

import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.Serializable

data class VideoItem(@TypeConverters(ObjectConverter::class)
                     val images: Image,
                     val year: String = "",
                     val description: String = "",
                     val title: String = ""): Serializable
class ObjectConverter {
    @TypeConverter
    fun fromObjectToJson(from: Image): String {
        val type = Types.newParameterizedType(Image::class.java)
        return Moshi.Builder().build().adapter<Image>(type).toJson(from)
    }

    @TypeConverter
    fun toObject(json: String): Image {
        val type = Types.newParameterizedType(Image::class.java)
        return Moshi.Builder().build().adapter<Image>(type).fromJson(json)
    }
}

