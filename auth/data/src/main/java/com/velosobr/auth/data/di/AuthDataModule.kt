package com.velosobr.auth.data.di

import com.velosobr.auth.data.EmailPatternValidator
import com.velosobr.auth.domain.PatternValidator
import com.velosobr.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
}