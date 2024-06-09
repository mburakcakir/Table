package com.mburakcakir.ui.qualifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mburakcakir.domain.model.Qualification


@Composable
fun Qualifications(qualifications: List<Qualification>?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 8.dp)
    ) {
        qualifications?.filter { it.description != null }?.forEach {
            Qualification(qualification = it)
            Spacer(modifier = Modifier.height(4.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun Qualification(qualification: Qualification) {
    Row(
        modifier = Modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .height(8.dp)
                .width(8.dp)
                .background(qualification.color ?: Color.LightGray)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = qualification.description!!, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}