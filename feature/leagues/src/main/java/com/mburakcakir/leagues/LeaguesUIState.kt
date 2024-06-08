package com.mburakcakir.leagues

import com.mburakcakir.network.model.LeaguesModel

data class LeaguesUIState(
    val isLoading: Boolean = false,
    val leagues: LeaguesModel? = null
)