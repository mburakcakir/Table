package com.mburakcakir.data.datasource

import com.mburakcakir.common.state.State
import com.mburakcakir.data.extension.handleRequest
import com.mburakcakir.model.leagues.LeaguesModel
import com.mburakcakir.model.standings.SeasonsModel
import com.mburakcakir.model.standings.StandingsModel
import com.mburakcakir.network.di.NetworkApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TableDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {
    fun getLeagues(): Flow<State<LeaguesModel>> = handleRequest {
        networkApi.getLeagues()
    }

    fun getStandings(
        leagueId: String,
        season: Int,
        sort: String
    ): Flow<State<StandingsModel>> = handleRequest {
            networkApi.getStandings(leagueId, season, sort)
        }

    fun getSeasons(league: String): Flow<State<SeasonsModel>> = handleRequest {
        networkApi.getSeasons(league)
    }
}