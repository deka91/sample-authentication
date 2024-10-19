package ch.dk.sampleauthentication.feature.confirmation.presentation

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import ch.dk.sampleauthentication.R
import ch.dk.sampleauthentication.core.presentation.components.PrimaryButton
import ch.dk.sampleauthentication.core.presentation.components.PrimaryHeader
import ch.dk.sampleauthentication.ui.theme.DIMENSIONS
import ch.dk.sampleauthentication.ui.theme.SampleAuthenticationTheme

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@Composable
fun ConfirmationScreen(state: ConfirmationState) {

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
                ConfirmationContent(state = state)
            }
        })
}

@Composable
private fun ConfirmationContent(state: ConfirmationState) {
    val activity = (LocalContext.current as? Activity)

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

        UserInfoItem(title = stringResource(R.string.title_name), value = state.name)
        UserInfoItem(title = stringResource(R.string.title_email), value = state.email)
        UserInfoItem(title = stringResource(R.string.title_birthday), value = state.birthday)

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.confirmation_finish),
            onClick = { activity?.finish() }
        )

        Spacer(modifier = Modifier.height(DIMENSIONS.baseline4))
    }
}

@Composable
private fun UserInfoItem(title: String, value: String) {
    Text(
        text = "$title: $value",
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold
    )
}


@Preview
@Composable
fun ConfirmationScreenPreview() {
    SampleAuthenticationTheme {
        ConfirmationScreen(state = ConfirmationState())
    }
}