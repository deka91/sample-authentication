package ch.dk.sampleauthentication.feature.confirmation.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ch.dk.sampleauthentication.feature.confirmation.domain.repository.ConfirmationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Deniz Kalem on 19.10.2024
 */
@HiltViewModel
class ConfirmationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val confirmationRepository: ConfirmationRepository
) : ViewModel() {

    val state = savedStateHandle.getStateFlow(
        key = "state", initialValue = ConfirmationState()
    )

    init {
        loadUserData()
    }

    private fun loadUserData() {
        updateState {
            it.copy(
                name = confirmationRepository.loadName(),
                email = confirmationRepository.loadEmail(),
                birthday = confirmationRepository.loadBirthday()
            )
        }
    }

    private fun updateState(update: (ConfirmationState) -> ConfirmationState) {
        savedStateHandle["state"] = update(state.value)
    }
}