package ch.dk.sampleauthentication.feature.registration.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import ch.dk.sampleauthentication.ui.theme.DIMENSIONS

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    state: TextInputFieldState,
    hint: String,
    keyboardOptions: KeyboardOptions,
    maxLength: Int? = null,
    onValueChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit
) {
    Box {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .onFocusChanged { onFocusChanged(it) },
            value = state.text,
            onValueChange = { input ->
                if (maxLength == null || input.length <= maxLength) {
                    onValueChange(input)
                }
            },
            singleLine = true,
            maxLines = 1,
            shape = MaterialTheme.shapes.small,
            keyboardOptions = keyboardOptions,
            isError = state.isError && !state.isCheckVisible
        )

        if (state.isHintVisible) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(DIMENSIONS.baseline2),
                text = hint,
                color = MaterialTheme.colorScheme.outline
            )
        }

        if (state.isCheckVisible) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(DIMENSIONS.baseline2),
                imageVector = Icons.Default.Check,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null,
            )
        }
    }
}