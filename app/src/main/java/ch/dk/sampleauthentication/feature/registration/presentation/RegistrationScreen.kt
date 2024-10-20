package ch.dk.sampleauthentication.feature.registration.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ch.dk.sampleauthentication.R
import ch.dk.sampleauthentication.core.presentation.components.PrimaryButton
import ch.dk.sampleauthentication.core.presentation.components.PrimaryHeader
import ch.dk.sampleauthentication.core.presentation.util.UiText
import ch.dk.sampleauthentication.feature.registration.presentation.InputConstants.DATE_MAX_LENGTH
import ch.dk.sampleauthentication.feature.registration.presentation.components.TextInputField
import ch.dk.sampleauthentication.ui.theme.DIMENSIONS
import ch.dk.sampleauthentication.ui.theme.SampleAuthenticationTheme

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@Composable
fun RegistrationScreen(state: RegistrationState, onEvent: (RegistrationEvent) -> Unit) {

    val errorSnackBarHostState = remember { SnackbarHostState() }
    val keyboard = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    if (state.isInputValid) onEvent(RegistrationEvent.OnSuccess)

    LaunchedEffect(
        key1 = state.nameError,
        key2 = state.emailError,
        key3 = state.birthdayError,
    ) {
        val errorMessages = mutableListOf<UiText>()

        state.nameError?.let { errorMessages.add(it) }
        state.emailError?.let { errorMessages.add(it) }
        state.birthdayError?.let { errorMessages.add(it) }

        if (errorMessages.isNotEmpty()) {
            val combinedMessage = errorMessages.joinToString("\n") { it.asString(context) }
            keyboard?.hide()
            errorSnackBarHostState.showSnackbar(combinedMessage)
            onEvent(RegistrationEvent.OnErrorMessageSeen)
        }
    }

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                PrimaryHeader(text = stringResource(id = R.string.registration_title))
                RegistrationContent(state = state, onEvent = onEvent)
            }
        }, snackbarHost = {
            SnackbarHost(hostState = errorSnackBarHostState) { data ->
                Snackbar(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                    snackbarData = data
                )
            }
        })
}

@Composable
private fun RegistrationContent(state: RegistrationState, onEvent: (RegistrationEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = DIMENSIONS.baseline2, topEnd = DIMENSIONS.baseline2))
            .background(MaterialTheme.colorScheme.background)
            .padding(DIMENSIONS.baseline2)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(DIMENSIONS.baseline2)
    ) {
        Text(
            modifier = Modifier.padding(vertical = DIMENSIONS.baseline3),
            text = stringResource(id = R.string.registration_description),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )

        TextInputField(
            modifier = Modifier.testTag(RegistrationTestTag.INPUT_NAME),
            state = state.name,
            hint = stringResource(R.string.title_name),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { onEvent(RegistrationEvent.OnNameChange(it)) },
            onFocusChanged = { onEvent(RegistrationEvent.OnNameFocusChange(it)) }
        )

        TextInputField(
            modifier = Modifier.testTag(RegistrationTestTag.INPUT_EMAIL),
            state = state.email,
            hint = stringResource(R.string.title_email),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { onEvent(RegistrationEvent.OnEmailChange(it)) },
            onFocusChanged = { onEvent(RegistrationEvent.OnEmailFocusChange(it)) }
        )

        TextInputField(
            modifier = Modifier.testTag(RegistrationTestTag.INPUT_BIRTHDAY),
            state = state.birthday,
            hint = stringResource(R.string.title_birthday),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLength = DATE_MAX_LENGTH,
            onValueChange = { onEvent(RegistrationEvent.OnBirthdayChange(it)) },
            onFocusChanged = { onEvent(RegistrationEvent.OnBirthdayFocusChange(it)) }
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(RegistrationTestTag.BUTTON_SUBMIT),
            text = stringResource(R.string.registration_submit),
            onClick = {
                onEvent(
                    RegistrationEvent.OnSubmit(
                        name = state.name.text,
                        email = state.email.text,
                        birthday = state.birthday.text
                    )
                )
            }
        )

        Spacer(modifier = Modifier.height(DIMENSIONS.baseline4))
    }
}

@PreviewLightDark
@Composable
private fun RegistrationScreenPreview() {
    SampleAuthenticationTheme {
        RegistrationScreen(state = RegistrationState(), onEvent = {})
    }
}

object InputConstants {
    const val DATE_MAX_LENGTH = 10
}

object RegistrationTestTag {
    const val INPUT_NAME = "registration_input_name"
    const val INPUT_EMAIL = "registration_input_email"
    const val INPUT_BIRTHDAY = "registration_input_birthday"
    const val BUTTON_SUBMIT = "registration_button_submit"
}