package com.mburakcakir.standings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mburakcakir.common.destination.TableDestination

fun NavGraphBuilder.standingsRoute(
    onTeamClick: () -> Unit
) {
    val route = TableDestination.Standings.route
    val leagueId = TableDestination.Standings.arguments?.get(0)
    val leagueIcon = TableDestination.Standings.arguments?.get(1)
    composable(
        "$route/{$leagueId}/{$leagueIcon}",
        arguments = listOf(
            navArgument("$leagueId") { type = NavType.StringType },
            navArgument("$leagueIcon") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        StandingsRoute(
            leagueId = backStackEntry.arguments?.getString("$leagueId") ?: "",
            leagueIcon = backStackEntry.arguments?.getString("$leagueIcon") ?: "",
            onTeamClick = onTeamClick
        )
    }
}

@Composable
fun StandingsRoute(
    leagueId: String,
    leagueIcon: String,
    onTeamClick: () -> Unit,
    viewModel: StandingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(leagueId) {
        viewModel.getSeasons(leagueId)
    }

    StandingsScreen(
        uiState = uiState.copy(leagueIcon = leagueIcon),
        onSeasonClick = { viewModel.getStandings(leagueId = leagueId, season = it) },
        onTeamClick = onTeamClick
    )
}
