package ch.dk.sampleauthentication.feature.registration.domain.usecase

import ch.dk.sampleauthentication.feature.registration.domain.repository.RegistrationRepository
import javax.inject.Inject

/**
 * Created by Deniz Kalem on 20.10.2024
 */
class SaveUserData @Inject constructor(private val registrationRepository: RegistrationRepository) {

    operator fun invoke(name: String, email: String, birthday: String) {
        registrationRepository.saveUserData(name = name, email = email, birthday = birthday)
    }
}