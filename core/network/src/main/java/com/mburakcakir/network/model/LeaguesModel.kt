package com.mburakcakir.network.model

import com.google.gson.annotations.SerializedName

data class LeaguesModel(
    @SerializedName("data")
    val leagues: List<League>? = null,
    override val status: Boolean? = null
): BaseModel

data class League(
    val abbr: String,
    val id: String,
    val logos: Logos,
    val name: String,
    val slug: String
)

data class Logos(
    val dark: String,
    val light: String
)