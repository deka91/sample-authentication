package ch.dk.sampleauthentication

import android.app.Application
import timber.log.Timber

/**
 * Created by Deniz Kalem on 18.10.2024
 */
class SampleAuthenticationApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}