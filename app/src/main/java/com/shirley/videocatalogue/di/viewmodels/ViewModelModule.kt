package com.shirley.videocatalogue.viewmodels.viewmodel

import android.arch.lifecycle.ViewModel
import com.shirley.videocatalogue.ShiftViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShiftViewModel::class)
    abstract fun bindShiftViewModel(shiftViewModel: ShiftViewModel): ViewModel

}