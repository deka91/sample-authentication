package ch.dk.sampleauthentication.feature.registration.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import ch.dk.sampleauthentication.ui.theme.DIMENSIONS
import ch.dk.sampleauthentication.ui.theme.Gray
import ch.dk.sampleauthentication.ui.theme.Green

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    state: TextInputFieldState,
    hint: String,
    onValueChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit
) {
    Box(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .onFocusChanged { onFocusChanged(it) },
            value = state.text,
            onValueChange = onValueChange,
            singleLine = true,
            maxLines = 1,
            shape = MaterialTheme.shapes.small,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.outline,
                unfocusedContainerColor = MaterialTheme.colorScheme.outline,
                disabledContainerColor = MaterialTheme.colorScheme.outline
            )
        )

        if (state.isHintVisible) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(DIMENSIONS.baseline2),
                text = hint,
                color = Gray
            )
        }

        if (state.isCheckVisible) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(DIMENSIONS.baseline2),
                imageVector = Icons.Default.Check,
                tint = Green,
                contentDescription = null,
            )
        }
    }
}