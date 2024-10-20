package ch.dk.sampleauthentication.feature.confirmation.domain.usecase

import ch.dk.sampleauthentication.core.domain.model.UserProfile
import ch.dk.sampleauthentication.feature.confirmation.domain.repository.ConfirmationRepository
import javax.inject.Inject

/**
 * Created by Deniz Kalem on 20.10.2024
 */
class LoadUserProfile @Inject constructor(private val confirmationRepository: ConfirmationRepository) {

    operator fun invoke(): UserProfile {
        val name = confirmationRepository.loadName()
        val email = confirmationRepository.loadEmail()
        val birthday = confirmationRepository.loadBirthday()

        return UserProfile(
            name = name,
            email = email,
            birthday = birthday
        )
    }
}