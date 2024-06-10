package com.mburakcakir.teamdetail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mburakcakir.common.extensions.loadImageWithUrl
import com.mburakcakir.common.extensions.notNull
import com.mburakcakir.common.extensions.notNullOrEmptyComposable
import com.mburakcakir.domain.model.Standing
import com.mburakcakir.domain.model.teamdetail.TeamDetail
import com.mburakcakir.network.model.Season
import com.mburakcakir.ui.header.Header
import com.mburakcakir.ui.loading.Loading
import com.mburakcakir.ui.seasondropdown.SeasonDropdownMenu
import com.mburakcakir.ui.teamstatistics.TeamStatistics

@Composable
fun TeamDetailScreen(
    uiState: TeamDetailUiState,
    onSeasonClick: (Season) -> Unit,
) {
    if (!uiState.isLoading) {
        Column {
            TeamHeader(
                uiState.teamDetail,
                uiState.seasonStart,
                uiState.seasonEnd,
                onSeasonClick
            )
            Spacer(modifier = Modifier.height(8.dp))
            AllSeasons(
                uiState.headerList,
                uiState.selectedSeasons,
                uiState.selectedStandings?.toList()
            )
        }
    } else {
        Loading()
    }
}

@Composable
fun TeamHeader(
    teamDetail: TeamDetail?,
    seasonStart: String?,
    seasonEnd: String?,
    onSeasonClick: (Season) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        teamDetail?.standings?.standing?.logo?.loadImageWithUrl(size = 48.dp)
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            teamDetail?.standings?.standing?.teamName.notNullOrEmptyComposable {
                Text(
                    text = "$it (${teamDetail?.standings?.standing?.abbreviation})",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            teamDetail?.standings?.leagueName.notNullOrEmptyComposable {
                Text(text = "$it ($seasonStart - $seasonEnd)", fontSize = 12.sp)
                Spacer(modifier = Modifier.height(4.dp))
            }
            SeasonDropdownMenu(seasons = teamDetail?.seasons) {
                onSeasonClick.invoke(it)
            }
        }
    }
}

@Composable
fun AllSeasons(
    headerList: MutableList<String>,
    seasons: List<Season?>?,
    standings: List<Standing?>?
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SeasonInfo(
            seasons = seasons,
            standings = standings,
            headers = headerList,
        )
    }
}

@Composable
fun SeasonInfo(
    seasons: List<Season?>?,
    standings: List<Standing?>?,
    headers: MutableList<String>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Header(headers)
        Seasons(seasons, standings)
    }
}

@Composable
fun Seasons(
    seasons: List<Season?>?,
    standings: List<Standing?>?
) {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        standings.notNull {
            this.itemsIndexed(items = it.filterNotNull()) { index, item ->
                Season(
                    item = item,
                    season = seasons?.get(index),
                    bgColor = if (index % 2 != 0) Color(0xFFf0eeed) else Color.White
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun Season(item: Standing, season: Season?, bgColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(bgColor)
            .padding(start = 8.dp, top = 6.dp, bottom = 6.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        season.notNull {
            Text(
                text = season?.displayName?.split(" ")?.get(0) ?: "",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Log.v("myItem", item.toString())
        TeamStatistics(values = item.values?.plus(item.rank.toString()))
        Spacer(modifier = Modifier.width(4.dp))
    }
    HorizontalDivider()
}
