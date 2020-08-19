package com.nikolam.restaurantownerdemo

import android.app.Application
import com.nikolam.core.di.firebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class RestaurantOwnerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@RestaurantOwnerApplication)
            modules(firebaseModule)
        }

        // This will initialise Timber
        // This will initialise Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
