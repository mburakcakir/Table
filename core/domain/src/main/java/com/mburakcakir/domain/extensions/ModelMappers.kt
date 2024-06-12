package com.mburakcakir.domain.extensions

import com.mburakcakir.domain.utils.QualificationHandler
import com.mburakcakir.domain.utils.StandingHandler
import com.mburakcakir.model.standings.StandingInfo
import com.mburakcakir.model.standings.Standings
import com.mburakcakir.model.standings.StandingsInfo

fun StandingsInfo.asStandingsResponseMapper(): Standings = Standings(
    name,
    standingInfos?.asStandingsMapper(),
    standingInfos?.asQualificationMapper()
)

private fun List<StandingInfo>.asStandingsMapper() = map { StandingHandler(it).toMapperModel() }
private fun List<StandingInfo>.asQualificationMapper() = QualificationHandler(this).toMapperModel()



