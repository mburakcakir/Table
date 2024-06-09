package com.mburakcakir.standings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mburakcakir.common.destination.TableDestination
import com.mburakcakir.domain.model.teamdetail.TeamDetail

fun NavGraphBuilder.standingsRoute(
    onTeamClick: (TeamDetail) -> Unit
) {
    val route = TableDestination.Standings.route
    val leagueId = TableDestination.Standings.arguments[0]
    val leagueIcon = TableDestination.Standings.arguments[1]

    composable(
        "$route/{$leagueId}/{$leagueIcon}",
        arguments = listOf(
            navArgument(leagueId) { type = NavType.StringType },
            navArgument(leagueIcon) { type = NavType.StringType }
        )
    ) {
        StandingsRoute(
            onTeamClick = onTeamClick
        )
    }
}

@Composable
fun StandingsRoute(
    onTeamClick: (TeamDetail) -> Unit,
    viewModel: StandingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    StandingsScreen(
        uiState = uiState,
        onSeasonClick = { viewModel.getStandings(season = it) },
        onTeamClick = onTeamClick
    )
}
