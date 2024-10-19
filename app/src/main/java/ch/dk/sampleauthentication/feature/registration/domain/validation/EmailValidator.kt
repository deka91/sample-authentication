package ch.dk.sampleauthentication.feature.registration.domain.validation

interface EmailValidator {
    fun isValidEmailPattern(email: String): Boolean
}