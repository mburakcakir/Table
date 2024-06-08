package com.mburakcakir.common.state

sealed class State<out T : Any> {
    data object Loading : State<Nothing>()
    data class Success<out T : Any>(val result: T) : State<T>()
    data class Error(val exception: Throwable) : State<Nothing>()
}
