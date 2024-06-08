package com.mburakcakir.standings

import com.mburakcakir.domain.model.Standings
import com.mburakcakir.network.model.SeasonInfo

data class StandingsUiState(
    val isLoading: Boolean = false,
    val seasons: SeasonInfo? = null,
    val standings: Standings? = null,
    val headerList: MutableList<String> = mutableListOf(),
    val leagueIcon: String? = null
)
