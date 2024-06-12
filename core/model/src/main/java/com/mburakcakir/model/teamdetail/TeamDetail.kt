package com.mburakcakir.model.teamdetail

import com.mburakcakir.model.standings.Season
import com.mburakcakir.model.standings.Standing

data class TeamDetail(
    val seasons: List<Season>?,
    val standings: TeamDetailStandings?
)

data class TeamDetailStandings(
    val leagueId: String? = null,
    val leagueName: String? = null,
    val selectedSeason: Season? = null,
    val selectedStanding: Standing? = null
)
