package ch.dk.sampleauthentication.feature.registration.domain.usecase

import ch.dk.sampleauthentication.R
import ch.dk.sampleauthentication.core.presentation.util.UiText
import ch.dk.sampleauthentication.feature.registration.domain.validation.ValidationResult

class ValidateName {

    companion object {
        const val MIN_NAME_LENGTH = 1
    }

    operator fun invoke(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(
                isSuccessful = false, error = UiText.LocalString(R.string.error_name_blank)
            )
        }

        if (name.trim().length >= MIN_NAME_LENGTH) {
            return ValidationResult(isSuccessful = true)
        }

        return ValidationResult(isSuccessful = false, error = UiText.LocalString(R.string.error_name_not_valid))
    }
}