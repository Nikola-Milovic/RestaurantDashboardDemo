package com.nikolam.menu

import android.app.Application
import com.nikolam.core.di.firebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import timber.log.Timber

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@TestApplication)
            modules()
        }
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }
}
