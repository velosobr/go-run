package com.velosobr.auth.presentation.di

import com.velosobr.auth.presentation.register.RegisterViewModel
import com.velosobr.auth.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}