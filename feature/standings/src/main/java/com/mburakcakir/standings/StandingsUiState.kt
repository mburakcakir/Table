package com.mburakcakir.standings

import com.mburakcakir.model.standings.Season
import com.mburakcakir.model.standings.SeasonInfo
import com.mburakcakir.model.standings.Standings

data class StandingsUiState(
    val isLoading: Boolean = false,
    val seasons: SeasonInfo? = null,
    val standings: Standings? = null,
    val headerList: MutableList<String> = mutableListOf(),
    val leagueId: String? = null,
    val leagueIcon: String? = null,
    val selectedSeason: Season? = null
)
