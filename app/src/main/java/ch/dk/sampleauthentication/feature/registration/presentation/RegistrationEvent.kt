package ch.dk.sampleauthentication.feature.registration.presentation

import androidx.compose.ui.focus.FocusState

/**
 * Created by Deniz Kalem on 18.10.2024
 */
sealed class RegistrationEvent {
    data class OnNameChange(val name: String) : RegistrationEvent()
    data class OnNameFocusChange(val focusState: FocusState) : RegistrationEvent()
    data class OnEmailChange(val emailAddress: String) : RegistrationEvent()
    data class OnEmailFocusChange(val focusState: FocusState) : RegistrationEvent()
    data class OnBirthdayChange(val birthday: String) : RegistrationEvent()
    data class OnBirthdayFocusChange(val focusState: FocusState) : RegistrationEvent()
    data object OnErrorMessageSeen : RegistrationEvent()
    data object OnSubmit : RegistrationEvent()
    data object OnSuccess : RegistrationEvent()
}