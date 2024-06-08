package com.mburakcakir.common.extensions

import androidx.compose.runtime.Composable

fun <T> Collection<T>?.notNullOrEmpty(f: (Collection<T>) -> Unit) {
    if (!this.isNullOrEmpty()) {
        f(this)
    }
}

fun <T> Collection<T>?.isNotNullAndEmpty() = !this.isNullOrEmpty()
@Composable
fun <T> Collection<T>?.notNullOrEmptyComposable(f: @Composable (Collection<T>) -> Unit) {
    if (!this.isNullOrEmpty()) {
        f(this)
    }
}
