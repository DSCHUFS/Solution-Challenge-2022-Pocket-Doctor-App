package com.hot.pocketdoctor.di

import com.hot.pocketdoctor.presentation.login.viewmodel.SignInViewModel
import com.hot.pocketdoctor.presentation.main.viewmodel.MainViewModel
import com.hot.pocketdoctor.presentation.signup.viewmodel.SignUpViewModel
import com.hot.pocketdoctor.presentation.treatment.viewmodel.TreatmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { TreatmentViewModel(get(), get(), get()) }
    viewModel { MainViewModel(get()) }
}