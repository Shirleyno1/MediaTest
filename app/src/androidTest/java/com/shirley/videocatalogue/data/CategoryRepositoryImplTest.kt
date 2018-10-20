package com.shirley.videocatalogue.data

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryRepositoryImplTest {
    @Mock
    lateinit var service: CategoryService
    @Mock
    lateinit var dao: CategoryDao

    private var categories: List<Category> = listOf(Category("Movie", listOf(VideoItem(Image("","")))), Category("Features", listOf(VideoItem(Image("","")))))

    @Test
    fun getAllCategories() {
        Mockito.`when`(service.getAllCategories()).thenReturn(Single.create { categories })
        Mockito.`when`(dao.getAllCategories()).thenReturn(Single.create{ emptyList<Category>()})
        val repository = CategoryRepositoryImpl(service, dao)
        val categoryList = repository.getAllCategories()
        categoryList.subscribe(object : SingleObserver<List<Category>>{
            override fun onSuccess(t: List<Category>) {
                Assert.assertEquals(t, categories)
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Assert.fail("CategoryRepositoryImplTest: " + e.message)
            }
        })
    }
}