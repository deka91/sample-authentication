package ch.dk.sampleauthentication.feature.registration.data.repository

import ch.dk.sampleauthentication.core.data.preferences.PreferenceManager
import ch.dk.sampleauthentication.feature.registration.domain.repository.RegistrationRepository
import javax.inject.Inject

/**
 * Created by Deniz Kalem on 19.10.2024
 */
class RegistrationRepositoryImpl @Inject constructor(private val preferenceManager: PreferenceManager) : RegistrationRepository {

    override fun saveUserData(name: String, email: String, birthday: String) {
        preferenceManager.writeUserData(name = name, email = email, birthday = birthday)
    }
}