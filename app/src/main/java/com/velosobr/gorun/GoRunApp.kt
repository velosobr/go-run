package com.velosobr.gorun

import android.app.Application
import com.velosobr.auth.data.di.authDataModule
import com.velosobr.auth.presentation.di.authViewModelModule
import com.velosobr.core.data.di.coreDataModule
import com.velosobr.gorun.di.appModule
import com.velosobr.run.presentation.di.runViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class GoRunApp: Application(){

    override fun onCreate(){
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@GoRunApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                runViewModelModule,
            )
        }

    }
}