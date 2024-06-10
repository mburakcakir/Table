package com.mburakcakir.network.model

import com.google.gson.annotations.SerializedName

data class SeasonsModel(
    @SerializedName("data")
    val seasonInfo: SeasonInfo?,
    override val status: Boolean?
) : BaseModel

data class SeasonInfo(
    val abbreviation: String? = null,
    val desc: String? = null,
    val name: String? = null,
    val seasons: List<Season>? = null
)

data class Type(
    val abbreviation: String?,
    val endDate: String?,
    val hasStandings: Boolean?,
    val id: String?,
    val name: String?,
    val startDate: String?
)

data class Season(
    val displayName: String?,
    val endDate: String?,
    val startDate: String?,
    val types: List<Type>?,
    val year: Int?
)