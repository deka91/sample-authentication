package ch.dk.sampleauthentication.feature.confirmation.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ch.dk.sampleauthentication.feature.confirmation.domain.usecase.ConfirmationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Deniz Kalem on 19.10.2024
 */
@HiltViewModel
class ConfirmationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val confirmationUseCases: ConfirmationUseCases
) : ViewModel() {

    val state = savedStateHandle.getStateFlow(
        key = "state", initialValue = ConfirmationState()
    )

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        updateState {
            confirmationUseCases.loadUserProfile().let { userProfile ->
                it.copy(
                    name = userProfile.name,
                    email = userProfile.email,
                    birthday = userProfile.birthday
                )
            }
        }
    }

    private fun updateState(update: (ConfirmationState) -> ConfirmationState) {
        savedStateHandle["state"] = update(state.value)
    }
}