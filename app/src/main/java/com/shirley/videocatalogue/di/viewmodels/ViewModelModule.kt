package com.shirley.videocatalogue.di.viewmodels

import android.arch.lifecycle.ViewModel
import com.shirley.videocatalogue.CategoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindShiftViewModel(shiftViewModel: CategoryViewModel): ViewModel

}