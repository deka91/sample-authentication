package ch.dk.sampleauthentication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Deniz Kalem on 18.10.2024
 */

@HiltAndroidApp
class SampleAuthenticationApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}