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

    private fun register(name: String, email: String, birthday: String) {
        val nameResult = registrationUseCases.validateName(name)
        val emailResult = registrationUseCases.validateEmail(email)
        val birthdayResult = registrationUseCases.validateBirthday(birthday)

        val isNameError = !nameResult.isSuccessful
        val isEmailError = !emailResult.isSuccessful
        val isBirthdayError = !birthdayResult.isSuccessful

        val errorMessage = when {
            isNameError -> nameResult.error
            isEmailError -> emailResult.error
            isBirthdayError -> birthdayResult.error
            else -> null
        }

        updateState {
            it.copy(
                name = it.name.copy(isError = isNameError),
                email = it.email.copy(isError = isEmailError),
                birthday = it.birthday.copy(isError = isBirthdayError),
                errorMessage = errorMessage
            )
        }

        if (!isNameError && !isEmailError && !isBirthdayError) {
            registrationUseCases.saveUserProfile(UserProfile(name, email, birthday))
            updateState { it.copy(isInputValid = true) }
        }
    }

    private fun updateState(update: (RegistrationState) -> RegistrationState) {
        savedStateHandle["state"] = update(state.value)
    }
}