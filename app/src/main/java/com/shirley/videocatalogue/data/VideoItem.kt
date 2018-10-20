package com.shirley.videocatalogue.data

import android.arch.persistence.room.Embedded

data class VideoItem(@Embedded val image: Image,
                     val year: Int = 0,
                     val description: String = "",
                     val title: String = "")

