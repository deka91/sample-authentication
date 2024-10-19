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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import ch.dk.sampleauthentication.R
import ch.dk.sampleauthentication.core.presentation.components.PrimaryButton
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
    LaunchedEffect(key1 = state.errorMessage, key2 = errorSnackBarHostState) {
        state.errorMessage?.let { message ->
            keyboard?.hide()
            errorSnackBarHostState.showSnackbar(message.asString(context))
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
                RegistrationHeader()
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
private fun RegistrationHeader() {
    Spacer(modifier = Modifier.height(DIMENSIONS.baseline5))
    Text(
        text = stringResource(id = R.string.registration_title),
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(modifier = Modifier.height(DIMENSIONS.baseline5))
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
            state = state.name,
            hint = stringResource(R.string.title_name),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { onEvent(RegistrationEvent.OnNameChange(it)) },
            onFocusChanged = { onEvent(RegistrationEvent.OnNameFocusChange(it)) }
        )

        TextInputField(
            state = state.email,
            hint = stringResource(R.string.title_email),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { onEvent(RegistrationEvent.OnEmailChange(it)) },
            onFocusChanged = { onEvent(RegistrationEvent.OnEmailFocusChange(it)) }
        )

        TextInputField(
            state = state.birthday,
            hint = stringResource(R.string.title_birthday),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { onEvent(RegistrationEvent.OnBirthdayChange(it)) },
            onFocusChanged = { onEvent(RegistrationEvent.OnBirthdayFocusChange(it)) }
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.registration_submit),
            onClick = { onEvent(RegistrationEvent.OnSubmit) }
        )

        Spacer(modifier = Modifier.height(DIMENSIONS.baseline4))
    }
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    SampleAuthenticationTheme {
        RegistrationScreen(state = RegistrationState(), onEvent = {})
    }
}