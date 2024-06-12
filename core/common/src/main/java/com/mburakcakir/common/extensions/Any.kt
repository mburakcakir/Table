package com.mburakcakir.common.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <I> I?.notNull(f: (it: I) -> Unit) {
    if (this != null) {
        f(this)
    }
}

fun Any?.toJson(): String? {
    val gson = Gson()
    return if (this != null) gson.toJson(this).encode() else null
}

inline fun <reified T> String?.getGsonFromJson(): T? {
    val type = object : TypeToken<T>() {}.type
    return Gson().fromJson<T>(this?.decode(), type)
}
