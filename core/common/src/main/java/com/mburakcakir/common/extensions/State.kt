package com.mburakcakir.common.extensions

import com.mburakcakir.common.state.State
import kotlinx.coroutines.flow.Flow

suspend fun <T : Any> Flow<State<T>>.collectAsState(
    onLoading: () -> Unit,
    onSuccess: (T) -> Unit,
    onError: ((Throwable) -> Unit)? = null
) {
    collect { state ->
        state.handle(onLoading, onSuccess, onError)
    }
}

private fun <T : Any> State<T>.handle(
    onLoading: () -> Unit,
    onSuccess: (result: T) -> Unit,
    onError: ((Throwable) -> Unit)? = null
) {
    when (this) {
        is State.Loading -> onLoading()
        is State.Success -> onSuccess(result)
        is State.Error -> onError?.invoke(exception)
    }
}