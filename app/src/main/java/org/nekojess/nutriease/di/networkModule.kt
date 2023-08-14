package org.nekojess.nutriease.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.nekojess.nutriease.api.cache
import org.nekojess.nutriease.api.okHttpClient
import org.nekojess.nutriease.api.retrofit
import org.nekojess.nutriease.api.service


val networkModule = module {
    single { cache(androidApplication()) }
    single { okHttpClient(get(), androidApplication()) }
    single { service(get()) }
    single { retrofit(get()) }
}

