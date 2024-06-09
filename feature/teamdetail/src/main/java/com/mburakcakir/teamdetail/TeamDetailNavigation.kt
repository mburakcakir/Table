package com.mburakcakir.teamdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mburakcakir.common.destination.TableDestination

fun NavGraphBuilder.teamDetailRoute() {
    val route = TableDestination.TeamDetail.route
    val argument = TableDestination.TeamDetail.arguments.first()
    composable(
        "$route/{${argument}}",
        arguments = listOf(
            navArgument(argument) { type = NavType.StringType },
        )
    ) {
        TeamDetailRoute()
    }
}

@Composable
fun TeamDetailRoute(
    viewModel: TeamDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    TeamDetailScreen(uiState)
}
