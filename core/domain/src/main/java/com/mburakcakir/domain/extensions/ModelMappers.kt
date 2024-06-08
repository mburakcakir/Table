package com.mburakcakir.domain.extensions

import com.mburakcakir.domain.model.Standings
import com.mburakcakir.domain.utils.QualificationHandler
import com.mburakcakir.domain.utils.StandingHandler
import com.mburakcakir.network.model.StandingInfo
import com.mburakcakir.network.model.StandingsInfo

fun StandingsInfo.asStandingsResponseMapper(): Standings = Standings(
    name,
    standingInfos?.asStandingsMapper(),
    standingInfos?.asQualificationMapper()
)

private fun List<StandingInfo>.asStandingsMapper() = map { StandingHandler(it).toMapperModel() }
private fun List<StandingInfo>.asQualificationMapper() = QualificationHandler(this).toMapperModel()



