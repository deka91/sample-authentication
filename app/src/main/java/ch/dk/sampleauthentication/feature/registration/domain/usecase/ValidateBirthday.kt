package ch.dk.sampleauthentication.feature.registration.domain.usecase

import ch.dk.sampleauthentication.R
import ch.dk.sampleauthentication.core.presentation.util.UiText
import ch.dk.sampleauthentication.feature.registration.domain.validation.ValidationResult
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by Deniz Kalem on 18.10.2024
 */
class ValidateBirthday {

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    private val minDate = dateFormat.parse("01.01.1900")
    private val maxDate = dateFormat.parse("31.12.2021")

    operator fun invoke(birthday: String): ValidationResult {
        if (birthday.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                error = UiText.LocalString(R.string.error_birthday_blank)
            )
        }

        if (birthday.length != 10) {
            return ValidationResult(
                isSuccessful = false,
                error = UiText.LocalString(R.string.error_birthday_not_valid)
            )
        }

        val date = try {
            dateFormat.parse(birthday)
        } catch (e: Exception) {
            Timber.e(e)
            null
        }

        return if (date == null || date.before(minDate) || date.after(maxDate)) {
            ValidationResult(
                isSuccessful = false,
                error = UiText.LocalString(R.string.error_birthday_not_valid)
            )
        } else {
            ValidationResult(isSuccessful = true)
        }
    }
}