package com.velosobr.core.data.di

import android.content.SharedPreferences
import com.velosobr.core.data.auth.EncryptedSessionStorage
import com.velosobr.core.data.networking.HttpClientFactory
import com.velosobr.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.core.scope.get
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}