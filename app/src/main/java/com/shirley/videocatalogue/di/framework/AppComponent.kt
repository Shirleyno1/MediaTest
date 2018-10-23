package com.shirley.videocatalogue.di.framework

import com.shirley.videocatalogue.di.viewmodels.ViewModelFactoryModule
import com.shirley.videocatalogue.di.viewmodels.ViewModelModule
import com.shirley.videocatalogue.fragment.CategoryListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ServiceModule::class, RepositoryModule::class, RoomModule::class, ViewModelFactoryModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(target: CategoryListFragment)
}