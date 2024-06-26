package com.mburakcakir.model.standings

import androidx.compose.ui.graphics.Color

data class Qualifications(
    val list: List<Qualification>
)

data class Qualification(
    val color: Color?,
    val description: String?
)