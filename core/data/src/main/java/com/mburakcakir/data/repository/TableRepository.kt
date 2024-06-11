package com.mburakcakir.data.repository

import com.mburakcakir.common.state.State
import com.mburakcakir.data.datasource.TableDataSource
import com.mburakcakir.model.leagues.LeaguesModel
import com.mburakcakir.model.standings.SeasonsModel
import com.mburakcakir.model.standings.StandingsModel
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