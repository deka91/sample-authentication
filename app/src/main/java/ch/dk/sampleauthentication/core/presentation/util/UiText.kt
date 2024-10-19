package ch.dk.sampleauthentication.core.presentation.util

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
sealed class UiText : Parcelable {
    data class RemoteString(val value: String) : UiText()
    class LocalString(@StringRes val id: Int, vararg val args: @RawValue Any) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is RemoteString -> value
            is LocalString -> stringResource(id, *args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is RemoteString -> value
            is LocalString -> context.getString(id, *args)
        }
    }
}