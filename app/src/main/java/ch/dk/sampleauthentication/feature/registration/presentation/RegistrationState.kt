package ch.dk.sampleauthentication.feature.registration.presentation

import android.os.Parcelable
import ch.dk.sampleauthentication.core.presentation.util.UiText
import ch.dk.sampleauthentication.feature.registration.presentation.components.TextInputFieldState
import kotlinx.parcelize.Parcelize

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@Parcelize
data class RegistrationState(
    val name: TextInputFieldState = TextInputFieldState(),
    val email: TextInputFieldState = TextInputFieldState(),
    val birthday: TextInputFieldState = TextInputFieldState(),
    val isLoading: Boolean = false,
    val isInputValid: Boolean = false,
    val errorMessage: UiText? = null
) : Parcelable