package com.mburakcakir.domain.model

import com.mburakcakir.network.model.Note

data class Standings(
    val leagueName: String? = null,
    val standings: List<Standing>? = null,
    val qualifications: Qualifications? = null
)

data class Standing(
    val teamName: String? = null,
    val abbreviation: String? = null,
    val logo: String? = null,
    val rank: String? = null,
    val note: Note? = null,
    val values: List<String>? = null
)