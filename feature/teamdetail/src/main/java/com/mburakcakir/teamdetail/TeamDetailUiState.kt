package com.mburakcakir.teamdetail

import com.mburakcakir.domain.model.Standing
import com.mburakcakir.domain.model.teamdetail.TeamDetail
import com.mburakcakir.network.model.Season

data class TeamDetailUiState(
    val isLoading: Boolean = false,
    val teamDetail: TeamDetail? = null,
    var seasonStart: String? = null,
    var seasonEnd: String? = null,
    val headerList: MutableList<String> = mutableListOf(),
    val selectedStandings: MutableList<Standing?>? = mutableListOf(),
    val selectedSeasons: MutableList<Season?>? = mutableListOf()
)