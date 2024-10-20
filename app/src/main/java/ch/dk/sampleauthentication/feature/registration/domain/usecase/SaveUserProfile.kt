package ch.dk.sampleauthentication.feature.registration.domain.usecase

import ch.dk.sampleauthentication.core.domain.model.UserProfile
import ch.dk.sampleauthentication.feature.registration.domain.repository.RegistrationRepository
import javax.inject.Inject

/**
 * Created by Deniz Kalem on 20.10.2024
 */
class SaveUserProfile @Inject constructor(private val registrationRepository: RegistrationRepository) {

    operator fun invoke(userProfile: UserProfile) {
        registrationRepository.saveUserProfile(userProfile)
    }
}