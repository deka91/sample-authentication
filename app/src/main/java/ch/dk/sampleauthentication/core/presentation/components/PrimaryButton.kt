package ch.dk.sampleauthentication.core.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ch.dk.sampleauthentication.ui.theme.DIMENSIONS

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(DIMENSIONS.baseline8),
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        enabled = isEnabled
    ) {
        Text(text = text.uppercase())
    }
}