package ch.dk.sampleauthentication.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ch.dk.sampleauthentication.core.presentation.navigation.AppRoutes.GRAPH_AUTHENTICATION
import ch.dk.sampleauthentication.core.presentation.navigation.AppRoutes.ROUTE_CONFIRMATION
import ch.dk.sampleauthentication.core.presentation.navigation.AppRoutes.ROUTE_REGISTRATION
import ch.dk.sampleauthentication.feature.confirmation.presentation.ConfirmationScreen
import ch.dk.sampleauthentication.feature.registration.presentation.RegistrationScreen
import ch.dk.sampleauthentication.feature.registration.presentation.RegistrationViewModel

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = GRAPH_AUTHENTICATION
    ) {
        authenticationGraph(navController = navController)
    }
}

private fun NavGraphBuilder.authenticationGraph(navController: NavHostController) {
    navigation(
        startDestination = ROUTE_REGISTRATION,
        route = GRAPH_AUTHENTICATION
    ) {
        composable(route = ROUTE_REGISTRATION) {
            val registrationViewModel = hiltViewModel<RegistrationViewModel>()
            val state by registrationViewModel.state.collectAsStateWithLifecycle()
            RegistrationScreen(state, onEvent = {})
        }

        composable(route = ROUTE_CONFIRMATION) {
            ConfirmationScreen()
        }
    }
}

object AppRoutes {
    const val GRAPH_AUTHENTICATION = "authentication"
    const val ROUTE_REGISTRATION = "registration"
    const val ROUTE_CONFIRMATION = "confirmation"
}