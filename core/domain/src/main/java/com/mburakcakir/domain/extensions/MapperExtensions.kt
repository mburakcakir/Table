package com.mburakcakir.domain.extensions

import com.mburakcakir.common.state.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun <R : Any, M : Any> Flow<State<R>>.mapModel(mapper: (R) -> M): Flow<State<M>> {
    return map { state ->
        when (state) {
            is State.Success -> State.Success(mapper(state.result))
            is State.Error -> state
            is State.Loading -> state
        }
    }
}
