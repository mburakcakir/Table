package com.mburakcakir.common.destination

enum class TableDestination(
    val route: String,
    val arguments: List<String> = listOf()
) {
    Leagues(
        route = "leagues"
    ),
    Standings(
        route = "standings",
        arguments = listOf("leagueId","leagueIcon")
    ),
    TeamDetail(
        route = "teamDetail",
        arguments = listOf("teamDetail")
    )
}


