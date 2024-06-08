package com.mburakcakir.domain.usecase

import com.mburakcakir.common.state.State
import com.mburakcakir.data.repository.TableRepository
import com.mburakcakir.network.model.SeasonsModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeasonsUseCase @Inject constructor(
    private val repository: TableRepository
) {
    operator fun invoke(league: String): Flow<State<SeasonsModel>> = repository.getSeasons(league)
}