package ch.dk.sampleauthentication.feature.registration.presentation

import androidx.compose.ui.focus.FocusState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ch.dk.sampleauthentication.feature.registration.domain.usecase.RegistrationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle, private val registrationUseCases: RegistrationUseCases
) : ViewModel() {

    val state = savedStateHandle.getStateFlow(
        key = "state", initialValue = RegistrationState()
    )

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.OnNameChange -> changeName(event.name)
            is RegistrationEvent.OnNameFocusChange -> changeNameFocus(event.focusState)
            is RegistrationEvent.OnEmailChange -> changeEmail(event.emailAddress)
            is RegistrationEvent.OnEmailFocusChange -> changeEmailFocus(event.focusState)
            is RegistrationEvent.OnBirthdayChange -> changeBirthday(event.birthday)
            is RegistrationEvent.OnBirthdayFocusChange -> changeBirthdayFocus(event.focusState)
            is RegistrationEvent.OnErrorMessageSeen -> errorMessageSeen()
            is RegistrationEvent.OnSubmit -> register()
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

    private fun changeEmail(emailAddress: String) {
        val result = registrationUseCases.validateEmail(emailAddress)
        updateState {
            it.copy(email = it.email.copy(text = emailAddress, isCheckVisible = result.isSuccessful))
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
            it.copy(
                birthday = it.birthday.copy(
                    text = birthday, isCheckVisible = result.isSuccessful
                )
            )
        }
    }

    private fun changeBirthdayFocus(focusState: FocusState) {
        updateState {
            it.copy(
                birthday = it.birthday.copy(
                    isHintVisible = it.birthday.text.isBlank() && !focusState.isFocused
                )
            )
        }
    }

    private fun errorMessageSeen() {
        updateState { it.copy(errorMessage = null) }
    }

    private fun register() {
        val nameResult = registrationUseCases.validateName(state.value.name.text)
        if (!nameResult.isSuccessful) {
            updateState { it.copy(errorMessage = nameResult.error) }
            return
        }

        val emailResult = registrationUseCases.validateEmail(state.value.email.text)
        if (!emailResult.isSuccessful) {
            updateState { it.copy(errorMessage = emailResult.error) }
            return
        }

        val birthdayResult = registrationUseCases.validateBirthday(state.value.birthday.text)
        if (!birthdayResult.isSuccessful) {
            updateState { it.copy(errorMessage = birthdayResult.error) }
            return
        }

        updateState { it.copy(isInputValid = true) }
    }

    private fun updateState(update: (RegistrationState) -> RegistrationState) {
        savedStateHandle["state"] = update(state.value)
    }
}