package ch.dk.sampleauthentication.feature.confirmation.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import ch.dk.sampleauthentication.R
import ch.dk.sampleauthentication.core.presentation.components.PrimaryHeader
import ch.dk.sampleauthentication.ui.theme.DIMENSIONS
import ch.dk.sampleauthentication.ui.theme.SampleAuthenticationTheme

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@Composable
fun ConfirmationScreen(state: ConfirmationState, onEvent: (ConfirmationEvent) -> Unit) {

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
                PrimaryHeader(text = stringResource(id = R.string.confirmation_title))
                ConfirmationContent(state = state, onEvent = onEvent)
            }
        })
}

@Composable
private fun ConfirmationContent(state: ConfirmationState, onEvent: (ConfirmationEvent) -> Unit) {
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
            text = stringResource(id = R.string.confirmation_description),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = stringResource(id = R.string.title_name) + ": " + state.name,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(id = R.string.title_email) + ": " + state.email,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(id = R.string.title_birthday) + ": " + state.birthday,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

    }
}

@Preview
@Composable
fun ConfirmationScreenPreview() {
    SampleAuthenticationTheme {
        ConfirmationScreen(state = ConfirmationState(), onEvent = {})
    }
}