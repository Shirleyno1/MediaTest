package com.shirley.videocatalogue.data

import io.reactivex.Single
import retrofit2.http.GET

interface CategoryService {

    @GET("video-catalogue/data.json")
    fun getAllCategories(): Single<List<Category>>

}