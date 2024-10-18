package ch.dk.sampleauthentication.feature.registration.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state = savedStateHandle.getStateFlow(
        key = "state",
        initialValue = RegistrationState()
    )

    private fun updateState(update: (RegistrationState) -> RegistrationState) {
        savedStateHandle["state"] = update(state.value)
    }
}