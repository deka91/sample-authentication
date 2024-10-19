package ch.dk.sampleauthentication.feature.registration.domain.validation

import ch.dk.sampleauthentication.core.presentation.util.UiText

data class ValidationResult(
    val isSuccessful: Boolean = false,
    val error: UiText? = null
)