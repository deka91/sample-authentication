package ch.dk.sampleauthentication.feature.registration.domain.repository

import ch.dk.sampleauthentication.core.domain.model.UserProfile

/**
 * Created by Deniz Kalem on 19.10.2024
 */
interface RegistrationRepository {
    fun saveUserProfile(userProfile: UserProfile)
}