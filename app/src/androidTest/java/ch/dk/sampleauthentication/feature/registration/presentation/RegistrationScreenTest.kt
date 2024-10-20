package ch.dk.sampleauthentication.feature.registration.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

/**
 * Created by Deniz Kalem on 19.10.2024
 */
class RegistrationScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun registrationScreenRendersCorrectly() {
        composeRule.setContent { RegistrationScreen(state = RegistrationState(), onEvent = {}) }

        composeRule.onNodeWithTag(RegistrationTestTag.INPUT_NAME).assertIsDisplayed()
        composeRule.onNodeWithTag(RegistrationTestTag.INPUT_EMAIL).assertIsDisplayed()
        composeRule.onNodeWithTag(RegistrationTestTag.INPUT_BIRTHDAY).assertIsDisplayed()
        composeRule.onNodeWithTag(RegistrationTestTag.BUTTON_SUBMIT).assertIsDisplayed()
    }
}