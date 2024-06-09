package com.mburakcakir.teamdetail

import com.mburakcakir.domain.model.teamdetail.TeamDetail

data class TeamDetailUiState(
    val isLoading: Boolean = false,
    val teamDetail: TeamDetail? = null,
    var seasonStart: String? = null,
    var seasonEnd: String? = null
)