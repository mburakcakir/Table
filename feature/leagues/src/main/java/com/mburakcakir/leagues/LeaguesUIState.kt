package com.mburakcakir.leagues

import com.mburakcakir.model.leagues.LeaguesModel

data class LeaguesUIState(
    val isLoading: Boolean = false,
    val leagues: LeaguesModel? = null
)