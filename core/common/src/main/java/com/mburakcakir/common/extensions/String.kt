package com.mburakcakir.common.extensions

import android.graphics.Color
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.ui.graphics.Color as ComposeColor

val String.hexColor
    get() = ComposeColor(Color.parseColor(this))

fun String.encode(): String = URLEncoder.encode(
    this,
    StandardCharsets.UTF_8.toString()
)

fun String.decode(): String = URLDecoder.decode(
    this,
    StandardCharsets.UTF_8.toString()
)

fun String?.notNullOrEmpty(f: (String) -> Unit) {
    if (!this.isNullOrEmpty()) {
        f(this)
    }
}

@Composable
fun String?.notNullOrEmptyComposable(f: @Composable (String) -> Unit) {
    if (!this.isNullOrEmpty()) {
        f(this)
    }
}

fun String?.isNotNullAndEmpty() = !this.isNullOrEmpty()

@Composable
fun String.loadImageWithUrl(size: Dp? = null, contentDescription: String? = null) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .decoderFactory(SvgDecoder.Factory())
            .data(this)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription ?: "contentDescription",
        modifier = Modifier.size(size ?: 24.dp)
    )
}