package com.mburakcakir.domain.model.teamdetail

import com.mburakcakir.domain.model.Standing
import com.mburakcakir.network.model.Season

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
