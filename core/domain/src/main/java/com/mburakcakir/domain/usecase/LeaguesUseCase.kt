package com.mburakcakir.domain.usecase

import com.mburakcakir.common.state.State
import com.mburakcakir.data.repository.TableRepository
import com.mburakcakir.model.leagues.LeaguesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LeaguesUseCase @Inject constructor(
    private val repository: TableRepository
) {
    operator fun invoke(): Flow<State<LeaguesModel>> = repository.getLeagues()
}