package org.nekojess.nutriease

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.nekojess.nutriease.di.ViewModelModule.viewModelModule

class NutriEaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NutriEaseApplication)
            modules(viewModelModule)
        }
    }
}