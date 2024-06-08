package com.mburakcakir.data.extension

import com.mburakcakir.common.state.State
import com.mburakcakir.network.model.BaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response


fun <T : Any> handleRequest(block: suspend () -> Response<T>): Flow<State<T>> = flow {
    emit(State.Loading)
    val state = block().asResult().fold(
        onSuccess = {
            if (it is BaseModel && it.status == true) {
                State.Success(it)
            } else {
                State.Error(Exception("Status is not 200"))
            }
        },
        onFailure = { State.Error(it) }
    )
    emit(state)
}.catch {
    emit(State.Error(it))
}

private fun <T> Response<T>.asResult(): Result<T> {
    return if (isSuccessful) {
        body()?.let {
            Result.success(it)
        } ?: Result.failure(NullPointerException("Response body is null"))
    } else {
        Result.failure(Throwable(errorBody()?.string() ?: "Unknown error"))
    }
}