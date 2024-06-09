package com.mburakcakir.teamdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mburakcakir.common.extensions.isNotNullAndEmpty
import com.mburakcakir.common.extensions.loadImageWithUrl
import com.mburakcakir.common.extensions.notNullOrEmptyComposable
import com.mburakcakir.domain.model.teamdetail.TeamDetailStandings
import com.mburakcakir.ui.loading.Loading

@Composable
fun TeamDetailScreen(uiState: TeamDetailUiState) {
    if (!uiState.isLoading) {
        Column {
            Header(
                uiState.teamDetail?.standings,
                uiState.seasonStart,
                uiState.seasonEnd
            )
        }
    } else {
        Loading()
    }
}

@Composable
fun Header(
    standing: TeamDetailStandings?,
    seasonStart: String?,
    seasonEnd: String?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        standing?.logo?.loadImageWithUrl(48.dp)
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            standing?.teamName.notNullOrEmptyComposable {
                Text(text = it)
                Spacer(modifier = Modifier.height(4.dp))
            }
            standing?.leagueName.notNullOrEmptyComposable {
                Text(text = it)
                Spacer(modifier = Modifier.height(4.dp))
            }
            if (seasonStart.isNotNullAndEmpty() && seasonEnd.isNotNullAndEmpty()) {
                Text(text = "$seasonStart - $seasonEnd")
            }
        }
    }
}