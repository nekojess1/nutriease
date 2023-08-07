package org.nekojess.nutriease.di

import org.nekojess.nutriease.ui.home.HomeViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.nekojess.nutriease.data.repository.patient.PatientRepository
import org.nekojess.nutriease.data.repository.patient.PatientRepositoryImpl
import org.nekojess.nutriease.data.repository.user.UserRepository
import org.nekojess.nutriease.data.repository.user.UserRepositoryImpl
import org.nekojess.nutriease.ui.createPatient.CreatePatientViewModel
import org.nekojess.nutriease.ui.login.LoginViewModel
import org.nekojess.nutriease.ui.signup.SignUpViewModel

object ViewModelModule {

    val viewModelModule = module {
        single<UserRepository> { UserRepositoryImpl() }
        single<PatientRepository> { PatientRepositoryImpl() }
        viewModel { HomeViewModel(get(), get()) }
        viewModel { LoginViewModel(get()) }
        viewModel { CreatePatientViewModel(get()) }
        viewModel { SignUpViewModel(get()) }
    }
}
