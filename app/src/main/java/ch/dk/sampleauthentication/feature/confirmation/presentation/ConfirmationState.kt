package ch.dk.sampleauthentication.feature.confirmation.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@Parcelize
data class ConfirmationState(
    val name: String = "",
    val email: String = "",
    val birthday: String = "",
) : Parcelable