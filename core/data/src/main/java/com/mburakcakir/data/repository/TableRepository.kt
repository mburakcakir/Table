package com.mburakcakir.data.repository

import com.mburakcakir.common.state.State
import com.mburakcakir.data.datasource.TableDataSource
import com.mburakcakir.network.model.LeaguesModel
import com.mburakcakir.network.model.SeasonsModel
import com.mburakcakir.network.model.StandingsModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TableRepository @Inject constructor(
    private val tableDataSource: TableDataSource
) {
    fun getLeagues(): Flow<State<LeaguesModel>> = tableDataSource.getLeagues()

    fun getStandings(
        leagueId: String,
        season: Int,
        sort: String
    ): Flow<State<StandingsModel>> = tableDataSource.getStandings(leagueId, season, sort)

    fun getSeasons(league: String): Flow<State<SeasonsModel>> =
        tableDataSource.getSeasons(league)
}