package ch.dk.sampleauthentication.feature.registration.presentation

import androidx.compose.ui.focus.FocusState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ch.dk.sampleauthentication.core.domain.model.UserProfile
import ch.dk.sampleauthentication.feature.registration.domain.usecase.RegistrationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val registrationUseCases: RegistrationUseCases
) : ViewModel() {

    val state = savedStateHandle.getStateFlow(
        key = "state", initialValue = RegistrationState()
    )

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.OnNameChange -> changeName(name = event.name)
            is RegistrationEvent.OnNameFocusChange -> changeNameFocus(focusState = event.focusState)
            is RegistrationEvent.OnEmailChange -> changeEmail(email = event.email)
            is RegistrationEvent.OnEmailFocusChange -> changeEmailFocus(focusState = event.focusState)
            is RegistrationEvent.OnBirthdayChange -> changeBirthday(birthday = event.birthday)
            is RegistrationEvent.OnBirthdayFocusChange -> changeBirthdayFocus(focusState = event.focusState)
            is RegistrationEvent.OnErrorMessageSeen -> errorMessageSeen()
            is RegistrationEvent.OnSubmit -> register(name = event.name, email = event.email, birthday = event.birthday)
            else -> Unit
        }
    }

    private fun changeName(name: String) {
        val result = registrationUseCases.validateName(name)
        updateState {
            it.copy(name = it.name.copy(text = name, isCheckVisible = result.isSuccessful))
        }
    }

    private fun changeNameFocus(focusState: FocusState) {
        updateState {
            it.copy(name = it.name.copy(isHintVisible = it.name.text.isBlank() && !focusState.isFocused))
        }
    }

    private fun changeEmail(email: String) {
        val result = registrationUseCases.validateEmail(email)
        updateState {
            it.copy(email = it.email.copy(text = email, isCheckVisible = result.isSuccessful))
        }
    }

    private fun changeEmailFocus(focusState: FocusState) {
        updateState {
            it.copy(email = it.email.copy(isHintVisible = it.email.text.isBlank() && !focusState.isFocused))
        }
    }

    private fun changeBirthday(birthday: String) {
        val result = registrationUseCases.validateBirthday(birthday)
        updateState {
            it.copy(birthday = it.birthday.copy(text = birthday, isCheckVisible = result.isSuccessful))
        }
    }

    private fun changeBirthdayFocus(focusState: FocusState) {
        updateState {
            it.copy(birthday = it.birthday.copy(isHintVisible = it.birthday.text.isBlank() && !focusState.isFocused))
        }
    }

    private fun errorMessageSeen() {
        updateState {
            it.copy(nameError = null, emailError = null, birthdayError = null)
        }
    }

    private fun register(name: String, email: String, birthday: String) {
        val nameValidation = registrationUseCases.validateName(name)
        val emailValidation = registrationUseCases.validateEmail(email)
        val birthdayValidation = registrationUseCases.validateBirthday(birthday)

        val isNameError = !nameValidation.isSuccessful
        val isEmailError = !emailValidation.isSuccessful
        val isBirthdayError = !birthdayValidation.isSuccessful

        updateState {
            it.copy(
                name = it.name.copy(text = name, isError = isNameError),
                email = it.email.copy(text = email, isError = isEmailError),
                birthday = it.birthday.copy(text = birthday, isError = isBirthdayError),
                nameError = if (isNameError) nameValidation.error else null,
                emailError = if (isEmailError) emailValidation.error else null,
                birthdayError = if (isBirthdayError) birthdayValidation.error else null,
                isInputValid = !isNameError && !isEmailError && !isBirthdayError
            )
        }

        if (nameValidation.isSuccessful && emailValidation.isSuccessful && birthdayValidation.isSuccessful) {
            registrationUseCases.saveUserProfile(UserProfile(name, email, birthday))
        }
    }

    private fun updateState(update: (RegistrationState) -> RegistrationState) {
        savedStateHandle["state"] = update(state.value)
    }
}