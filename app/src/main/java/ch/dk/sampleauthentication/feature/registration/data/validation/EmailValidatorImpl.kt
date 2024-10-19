package ch.dk.sampleauthentication.feature.registration.data.validation

import android.util.Patterns
import ch.dk.sampleauthentication.feature.registration.domain.validation.EmailValidator

class EmailValidatorImpl : EmailValidator {

    override fun isValidEmailPattern(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}