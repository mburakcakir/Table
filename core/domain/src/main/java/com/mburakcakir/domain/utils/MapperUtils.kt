package com.mburakcakir.domain.utils

import com.mburakcakir.common.extensions.hexColor
import com.mburakcakir.model.standings.Note
import com.mburakcakir.model.standings.Qualification
import com.mburakcakir.model.standings.Qualifications
import com.mburakcakir.model.standings.Standing
import com.mburakcakir.model.standings.StandingInfo

class QualificationHandler(standingInfos: List<StandingInfo>) {

    private val notes: List<Note> = standingInfos.mapNotNull { it.note }

    fun toMapperModel() = Qualifications(
        list = getQualifications()
    )

    private fun getQualifications(): List<Qualification> {
        val groupedNotes = notes.groupBy { it.description }

        return groupedNotes.map { (description, notes) ->
            val color = notes.firstOrNull()?.color?.hexColor
            Qualification(color, description)
        }.toMutableList()
    }
}

class StandingHandler(private val standingInfo: StandingInfo) {

    private val emptyLogo = "https://a.espncdn.com/combiner/i?img=/i/teamlogos/soccer/500/default-team-logo-500.png"
    fun toMapperModel() = Standing(
        teamName = standingInfo.team?.name,
        abbreviation = standingInfo.team?.abbreviation,
        logo = standingInfo.team?.logos?.get(0)?.href ?: emptyLogo,
        rank = standingInfo.stats?.find { it.name == "rank" }?.value.toString(),
        note = standingInfo.note,
        values = standingInfo.handleValues()
    )

    private fun StandingInfo.handleValues(): List<String> {
        val point = stats?.find { it.name == "points" }?.value.toString()
        val average = stats?.find { it.name == "pointDifferential" }?.value.toString()
        val pointsAgainst = stats?.find { it.name == "pointsAgainst" }?.value.toString()
        val pointsFor = stats?.find { it.name == "pointsFor" }?.value.toString()
        val losses = stats?.find { it.name == "losses" }?.value.toString()
        val ties = stats?.find { it.name == "ties" }?.value.toString()
        val wins = stats?.find { it.name == "wins" }?.value.toString()
        val gamesPlayed = stats?.find { it.name == "gamesPlayed" }?.value.toString()

        return mutableListOf(point, average, pointsAgainst, pointsFor, losses, ties, wins, gamesPlayed).reversed()
    }
}
