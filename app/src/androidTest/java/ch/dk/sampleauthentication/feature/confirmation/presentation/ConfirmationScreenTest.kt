package ch.dk.sampleauthentication.feature.confirmation.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

/**
 * Created by Deniz Kalem on 20.10.2024
 */
class ConfirmationScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun confirmationScreenRendersCorrectly() {
        composeRule.setContent { ConfirmationScreen(state = ConfirmationState()) }

        composeRule.onNodeWithTag(ConfirmationTestTag.BUTTON_FINISH).assertIsDisplayed()
    }
}