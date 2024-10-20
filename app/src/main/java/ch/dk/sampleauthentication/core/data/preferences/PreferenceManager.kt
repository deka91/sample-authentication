package ch.dk.sampleauthentication.core.data.preferences

import android.content.SharedPreferences
import ch.dk.sampleauthentication.core.domain.model.UserProfile
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Deniz Kalem on 19.10.2024
 */

class PreferenceManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun writeUserProfile(userProfile: UserProfile) {
        sharedPreferences.edit().apply {
            putString(KEY_NAME, userProfile.name)
            putString(KEY_EMAIL, userProfile.email)
            putString(KEY_BIRTHDAY, userProfile.birthday)
            apply()
        }
        Timber.i("User profile saved successfully: Name: ${userProfile.name}, Email: ${userProfile.email}, Birthday: ${userProfile.birthday}")
    }

    fun readName(): String? = sharedPreferences.getString(KEY_NAME, null)

    fun readEmail(): String? = sharedPreferences.getString(KEY_EMAIL, null)

    fun readBirthday(): String? = sharedPreferences.getString(KEY_BIRTHDAY, null)

    companion object {
        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"
        private const val KEY_BIRTHDAY = "birthday"
    }
}