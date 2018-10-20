package com.shirley.videocatalogue.data

import io.reactivex.Single

class CategoryRepositoryImpl(private val service: CategoryService, private val dao: CategoryDao): CategoryRepository{
    var cacheeResult: List<Category>? = null
    override fun getAllCategories(): Single<List<Category>> {
        val remoteResult: Single<List<Category>> = service.getAllCategories().doAfterSuccess {
            it?.let {
                cacheeResult = it
                dao.saveCatogories(it)
            }
        }
        cacheeResult?.let { return Single.just(cacheeResult) }

        val localResult: Single<List<Category>> = dao.getAllCategories()
        return localResult.filter{
            !it.isEmpty()
        }.switchIfEmpty(remoteResult)
    }
}