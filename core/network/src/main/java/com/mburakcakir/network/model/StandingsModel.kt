package com.mburakcakir.network.model

import com.google.gson.annotations.SerializedName

data class StandingsModel(
    @SerializedName("data")
    val standingsInfo: StandingsInfo? = null,
    override val status: Boolean? = null
): BaseModel

data class StandingsInfo(
    val abbreviation: String? = null,
    val name: String? = null,
    val season: Int? = null,
    val seasonDisplay: String? = null,
    @SerializedName("standings")
    val standingInfos: List<StandingInfo>? = null
)

data class StandingInfo(
    val note: Note?,
    val stats: List<Stat>?,
    val team: Team?
)

data class Stat(
    val abbreviation: String?,
    val description: String?,
    val displayName: String?,
    val displayValue: String?,
    val id: String?,
    val name: String?,
    val shortDisplayName: String?,
    val summary: String?,
    val type: String?,
    val value: Int?
)

data class Logo(
    val alt: String?,
    val height: Int?,
    val href: String?,
    val lastUpdated: String?,
    val rel: List<String>?,
    val width: Int?
)

data class Team(
    val abbreviation: String?,
    val displayName: String?,
    val id: String?,
    val isActive: Boolean?,
    val isNational: Boolean?,
    val location: String?,
    val logos: List<Logo>?,
    val name: String?,
    val shortDisplayName: String?,
    val uid: String?
)

data class Note(
    val color: String?,
    val description: String?,
    val rank: Int?
)