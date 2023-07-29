package org.nekojess.nutriease.di


import org.nekojess.nutriease.ui.home.HomeViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.nekojess.nutriease.data.PatientsRepository
import org.nekojess.nutriease.data.UserRepository

object ViewModelModule {

    val viewModelModule = module {
        single { UserRepository() }
        single { PatientsRepository() }
        viewModel { HomeViewModel(get(), get()) }
    }
}
