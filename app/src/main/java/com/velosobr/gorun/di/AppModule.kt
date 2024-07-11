package com.velosobr.gorun.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.velosobr.core.data.auth.EncryptedSessionStorage
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "auth_prefs",
            MasterKey(androidApplication()),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}