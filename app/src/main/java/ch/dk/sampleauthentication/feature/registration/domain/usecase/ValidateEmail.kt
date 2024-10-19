package ch.dk.sampleauthentication.feature.registration.domain.usecase

import ch.dk.sampleauthentication.R
import ch.dk.sampleauthentication.core.presentation.util.UiText
import ch.dk.sampleauthentication.feature.registration.domain.validation.EmailValidator
import ch.dk.sampleauthentication.feature.registration.domain.validation.ValidationResult

class ValidateEmail(private val emailValidator: EmailValidator) {

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                error = UiText.LocalString(R.string.error_email_blank)
            )
        }

        if (!emailValidator.isValidEmailPattern(email)) {
            return ValidationResult(
                isSuccessful = false,
                error = UiText.LocalString(R.string.error_email_not_valid)
            )
        }

        return ValidationResult(isSuccessful = true)
    }
}