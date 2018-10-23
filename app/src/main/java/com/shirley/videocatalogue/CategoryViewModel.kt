package com.shirley.videocatalogue

import android.arch.lifecycle.MutableLiveData
import com.shirley.videocatalogue.base.BaseViewModel
import com.shirley.videocatalogue.data.Category
import com.shirley.videocatalogue.data.CategoryRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryViewModel @Inject constructor(private val shiftRepository: CategoryRepository) : BaseViewModel() {
    var categories = MutableLiveData<List<Category>>()
    lateinit var disposable: Disposable

    fun getAllCategories() {
          shiftRepository.getAllCategories()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Category>> {
                    override fun onSuccess(list: List<Category>) {
                        categories.value = list
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        message.value = e.message
                    }
                })
    }

    override fun onCleared() {
        super.onCleared()
    }
}