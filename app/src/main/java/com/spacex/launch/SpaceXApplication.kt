package com.spacex.launch

import android.app.Application
import timber.log.Timber

/**
 * @author Hari Hara Sudhan.N
 */
class SpaceXApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}