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
import java.net.URLEncoder
import androidx.compose.ui.graphics.Color as ComposeColor

val String.hexColor
    get() = ComposeColor(Color.parseColor(this))

fun String.encode(): String = URLEncoder.encode(
    this,
    "UTF-8"
)

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