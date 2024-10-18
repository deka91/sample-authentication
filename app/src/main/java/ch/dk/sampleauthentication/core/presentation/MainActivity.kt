package ch.dk.sampleauthentication.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import ch.dk.sampleauthentication.core.presentation.navigation.AppNavigation
import ch.dk.sampleauthentication.ui.theme.SampleAuthenticationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleAuthenticationTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}