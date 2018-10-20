package com.shirley.videocatalogue.di.framework

import com.shirley.videocatalogue.MainActivity
import com.shirley.videocatalogue.viewmodels.viewmodel.ViewModelFactoryModule
import com.shirley.videocatalogue.viewmodels.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ServiceModule::class, RepositoryModule::class, RoomModule::class, ViewModelFactoryModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(target: MainActivity)
}