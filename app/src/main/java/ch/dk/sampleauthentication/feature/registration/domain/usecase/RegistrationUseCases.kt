package ch.dk.sampleauthentication.feature.registration.domain.usecase


data class RegistrationUseCases(
    val validateName: ValidateName,
    val validateEmail: ValidateEmail,
    val validateBirthday: ValidateBirthday
)