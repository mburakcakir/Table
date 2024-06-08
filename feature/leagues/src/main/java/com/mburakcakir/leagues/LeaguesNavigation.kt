package com.mburakcakir.leagues

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mburakcakir.common.destination.TableDestination


fun NavGraphBuilder.leaguesRoute(onNavigateStandings: (String, String) -> Unit) {
    composable(TableDestination.Leagues.route) {
        LeaguesRoute(onNavigateStandings)
    }
}

@Composable
fun LeaguesRoute(
    onNavigateStandings: (String, String) -> Unit,
    viewModel: LeaguesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    LeaguesScreen(leaguesUIState = uiState, onNavigateStandings = onNavigateStandings)
}