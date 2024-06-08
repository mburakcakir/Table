package com.mburakcakir.ui.teamstatistics

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TeamStatistics(values: List<String>?) {
    values?.forEachIndexed { index, it ->
        if (index == (values.size.minus(1) ?: 0)) {
            TeamStatistic(info = it, fontWeight = FontWeight.Bold)
        } else {
            TeamStatistic(info = it)
        }
    }
}

@Composable
fun TeamStatistic(info: String, fontWeight: FontWeight = FontWeight.Normal) {
    Text(
        text = info,
        fontWeight = fontWeight,
        fontSize = 12.sp,
        maxLines = 1,
        modifier = Modifier.width(24.dp),
        textAlign = TextAlign.Center
    )
}
