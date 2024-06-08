package com.mburakcakir.common.extensions

inline fun <I> I?.notNull(f: (it: I) -> Unit) {
    if (this != null) {
        f(this)
    }
}