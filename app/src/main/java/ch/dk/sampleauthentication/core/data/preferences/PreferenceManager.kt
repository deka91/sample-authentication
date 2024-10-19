package ch.dk.sampleauthentication.core.data.preferences

import android.content.SharedPreferences
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Deniz Kalem on 19.10.2024
 */

class PreferenceManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun writeUserData(name: String, email: String, birthday: String) {
        sharedPreferences.edit().apply {
            putString(KEY_NAME, name)
            putString(KEY_EMAIL, email)
            putString(KEY_BIRTHDAY, birthday)
            apply()
        }
        Timber.i("User data saved successfully: Name: $name, Email: $email, Birthday: $birthday")
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