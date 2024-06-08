package com.mburakcakir.table.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mburakcakir.common.destination.TableDestination
import com.mburakcakir.common.extensions.encode
import com.mburakcakir.leagues.leaguesRoute
import com.mburakcakir.standings.standingsRoute
import com.mburakcakir.teamdetail.teamDetailRoute

@Composable
fun TableAppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TableDestination.Leagues.route,
        builder = {
            leaguesRoute(
                onNavigateStandings = { leagueId, leagueIcon ->
                    navController.navigate(
                        "${TableDestination.Standings.route}/$leagueId/${leagueIcon.encode()}"
                    )
                }
            )
            standingsRoute(
                onTeamClick = {
                    navController.navigate(TableDestination.TeamDetail.route)
                }
            )
            teamDetailRoute()
        }
    )
}