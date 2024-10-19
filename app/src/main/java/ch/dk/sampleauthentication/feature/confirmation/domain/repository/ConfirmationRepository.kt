package ch.dk.sampleauthentication.feature.confirmation.domain.repository

/**
 * Created by Deniz Kalem on 19.10.2024
 */
interface ConfirmationRepository {
    fun loadName(): String
    fun loadEmail(): String
    fun loadBirthday(): String
}