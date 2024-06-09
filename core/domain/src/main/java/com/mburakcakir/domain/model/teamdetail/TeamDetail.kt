package com.mburakcakir.domain.model.teamdetail

import com.mburakcakir.domain.model.Qualifications
import com.mburakcakir.domain.model.Standing
import com.mburakcakir.network.model.Season

data class TeamDetail(
    val seasons: List<Season>?,
    val standings: TeamDetailStandings?
)

data class TeamDetailStandings(
    val leagueName: String? = null,
    val teamName: String? = null,
    val logo: String? = null,
    val standings: List<Standing>? = null,
    val qualifications: Qualifications? = null
)
