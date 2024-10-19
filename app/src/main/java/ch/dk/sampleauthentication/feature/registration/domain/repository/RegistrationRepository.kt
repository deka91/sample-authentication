package ch.dk.sampleauthentication.feature.registration.domain.repository

/**
 * Created by Deniz Kalem on 19.10.2024
 */
interface RegistrationRepository {
    fun saveUserData(name: String, email: String, birthday: String)
}