package com.mburakcakir.teamdetail

import com.mburakcakir.model.standings.Season
import com.mburakcakir.model.standings.Standing
import com.mburakcakir.model.teamdetail.TeamDetail

data class TeamDetailUiState(
    val isLoading: Boolean = false,
    val teamDetail: TeamDetail? = null,
    var seasonStart: String? = null,
    var seasonEnd: String? = null,
    val headerList: MutableList<String> = mutableListOf(),
    val selectedStandings: MutableList<StandingSeason?>? = mutableListOf(),
)

data class StandingSeason(
    val standings: Standing?,
    val season: Season?
)