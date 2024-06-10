package com.mburakcakir.standings

import com.mburakcakir.domain.model.Standings
import com.mburakcakir.network.model.Season
import com.mburakcakir.network.model.SeasonInfo

data class StandingsUiState(
    val isLoading: Boolean = false,
    val seasons: SeasonInfo? = null,
    val standings: Standings? = null,
    val headerList: MutableList<String> = mutableListOf(),
    val leagueId: String? = null,
    val leagueIcon: String? = null,
    val selectedSeason: Season? = null
)
