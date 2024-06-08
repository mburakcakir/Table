package com.mburakcakir.teamdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TeamDetailScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Team Detail", modifier = Modifier.align(Alignment.Center))
    }
}