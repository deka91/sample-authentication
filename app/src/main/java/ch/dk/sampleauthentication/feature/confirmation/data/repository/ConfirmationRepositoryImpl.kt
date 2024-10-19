package ch.dk.sampleauthentication.feature.confirmation.data.repository

import ch.dk.sampleauthentication.core.data.preferences.PreferenceManager
import ch.dk.sampleauthentication.feature.confirmation.domain.repository.ConfirmationRepository
import javax.inject.Inject

/**
 * Created by Deniz Kalem on 19.10.2024
 */
class ConfirmationRepositoryImpl @Inject constructor(private val preferenceManager: PreferenceManager) : ConfirmationRepository {

    override fun loadName(): String = preferenceManager.readName() ?: ""

    override fun loadEmail(): String = preferenceManager.readEmail() ?: ""

    override fun loadBirthday(): String = preferenceManager.readBirthday() ?: ""
}