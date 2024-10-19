package ch.dk.sampleauthentication.core.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ch.dk.sampleauthentication.ui.theme.DIMENSIONS

/**
 * Created by Deniz Kalem on 19.10.2024
 */
@Composable
fun PrimaryHeader(modifier: Modifier = Modifier, text: String) {
    Spacer(modifier = Modifier.height(DIMENSIONS.baseline5))
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(modifier = Modifier.height(DIMENSIONS.baseline5))
}