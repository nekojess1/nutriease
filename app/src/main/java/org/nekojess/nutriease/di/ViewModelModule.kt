package org.nekojess.nutriease.di

import org.nekojess.nutriease.ui.home.HomeViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.nekojess.nutriease.data.PatientRepository
import org.nekojess.nutriease.data.UserRepository
import org.nekojess.nutriease.ui.createPatient.CreatePatientViewModel
import org.nekojess.nutriease.ui.login.LoginViewModel

object ViewModelModule {

    val viewModelModule = module {
        single { UserRepository() }
        single { PatientRepository() }
        viewModel { HomeViewModel(get(), get()) }
        viewModel { LoginViewModel(get()) }
        viewModel { CreatePatientViewModel(get()) }
    }
}
