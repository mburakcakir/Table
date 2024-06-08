package com.mburakcakir.teamdetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mburakcakir.common.destination.TableDestination

fun NavGraphBuilder.teamDetailRoute() {
    composable(TableDestination.TeamDetail.route) {
        TeamDetailRoute()
    }
}

@Composable
fun TeamDetailRoute(
    viewModel: TeamDetailViewModel = hiltViewModel()
) {
    TeamDetailScreen()
}
