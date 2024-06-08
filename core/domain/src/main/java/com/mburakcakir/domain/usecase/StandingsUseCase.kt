package com.mburakcakir.domain.usecase

import com.mburakcakir.common.state.State
import com.mburakcakir.data.repository.TableRepository
import com.mburakcakir.domain.extensions.asStandingsResponseMapper
import com.mburakcakir.domain.extensions.mapModel
import com.mburakcakir.domain.model.Standings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StandingsUseCase @Inject constructor(
    private val repository: TableRepository
) {
    operator fun invoke(
        leagueId: String,
        season: Int,
        sort: String
    ): Flow<State<Standings>> = repository.getStandings(leagueId, season, sort).mapModel {
        it.standingsInfo?.asStandingsResponseMapper() ?: Standings()
    }
}

