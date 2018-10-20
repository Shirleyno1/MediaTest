package com.shirley.videocatalogue

import android.app.Application
import com.facebook.stetho.Stetho
import com.shirley.videocatalogue.di.framework.AppComponent
import com.shirley.videocatalogue.di.framework.AppModule
import com.shirley.videocatalogue.di.framework.DaggerAppComponent

class SBSApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initDagger(app: SBSApplication): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule(app))
                .build()
    }
}