package com.shirley.videocatalogue.data

import io.reactivex.Single

interface CategoryRepository {

    fun getAllCategories(): Single<List<Category>>

}