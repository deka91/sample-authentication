package ch.dk.sampleauthentication.feature.confirmation.presentation

/**
 * Created by Deniz Kalem on 19.10.2024
 */
sealed class ConfirmationEvent {
    data object OnFinish : ConfirmationEvent()
}