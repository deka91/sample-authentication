package ch.dk.sampleauthentication.feature.registration.presentation.components

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@Parcelize
data class TextInputFieldState(
    val text: String = "",
    val isHintVisible: Boolean = true,
    val isCheckVisible: Boolean = false,
    val isError: Boolean = false
) : Parcelable