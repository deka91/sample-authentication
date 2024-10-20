package ch.dk.sampleauthentication.feature.registration.presentation

import androidx.compose.ui.focus.FocusState

/**
 * Created by Deniz Kalem on 18.10.2024
 */
sealed class RegistrationEvent {
    data class OnNameChange(val name: String) : RegistrationEvent()
    data class OnNameFocusChange(val focusState: FocusState) : RegistrationEvent()
    data class OnEmailChange(val email: String) : RegistrationEvent()
    data class OnEmailFocusChange(val focusState: FocusState) : RegistrationEvent()
    data class OnBirthdayChange(val birthday: String) : RegistrationEvent()
    data class OnBirthdayFocusChange(val focusState: FocusState) : RegistrationEvent()
    data object OnErrorMessageSeen : RegistrationEvent()
    data class OnSubmit(val name: String, val email: String, val birthday: String) : RegistrationEvent()
    data object OnSuccess : RegistrationEvent()
}