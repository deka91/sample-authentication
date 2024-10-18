package ch.dk.sampleauthentication.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Deniz Kalem on 18.10.2024
 */
data class Dimensions(
    val baseline0x25: Dp,
    val baseline0x5: Dp,
    val baseline1: Dp,
    val baseline1x5: Dp,
    val baseline2: Dp
)

val DIMENSIONS = Dimensions(
    baseline0x25 = 2.dp,
    baseline0x5 = 4.dp,
    baseline1 = 8.dp,
    baseline1x5 = 12.dp,
    baseline2 = 16.dp,
)