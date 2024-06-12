package com.mburakcakir.standings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mburakcakir.common.extensions.hexColor
import com.mburakcakir.common.extensions.isNotNullAndEmpty
import com.mburakcakir.common.extensions.loadImageWithUrl
import com.mburakcakir.common.extensions.notNullOrEmptyComposable
import com.mburakcakir.model.standings.Note
import com.mburakcakir.model.standings.Season
import com.mburakcakir.model.standings.Standing
import com.mburakcakir.model.standings.Standings
import com.mburakcakir.model.teamdetail.TeamDetail
import com.mburakcakir.model.teamdetail.TeamDetailStandings
import com.mburakcakir.ui.header.Header
import com.mburakcakir.ui.loading.Loading
import com.mburakcakir.ui.qualifications.Qualifications
import com.mburakcakir.ui.seasondropdown.SeasonDropdownMenu
import com.mburakcakir.ui.teamstatistics.TeamStatistics
import java.util.Locale

@Composable
fun StandingsScreen(
    uiState: StandingsUiState,
    onSeasonClick: (Season) -> Unit,
    onTeamClick: (TeamDetail) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        uiState.standings?.standings.notNullOrEmptyComposable {
            DropDownHeader(
                uiState.standings?.leagueName,
                uiState.leagueIcon,
                uiState.seasons?.seasons,
                onSeasonClick
            )
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (uiState.standings?.standings.isNotNullAndEmpty() && !uiState.isLoading) {
                StandingsInfo(
                    standings = uiState.standings,
                    headers = uiState.headerList,
                    onTeamClickNotifier = { standing ->
                        val teamDetail = uiState.standings?.let { standings ->
                            TeamDetail(
                                seasons = uiState.seasons?.seasons,
                                standings = TeamDetailStandings(
                                    leagueId = uiState.leagueId,
                                    leagueName = standings.leagueName,
                                    selectedSeason = uiState.selectedSeason,
                                    selectedStanding = standing
                                )
                            )
                        }
                        teamDetail?.let { onTeamClick(it) }
                    }
                )
            } else {
                Loading()
            }

            if (uiState.isLoading)
                Loading()
        }
    }
}

@Composable
fun DropDownHeader(
    leagueName: String?,
    leagueIcon: String?,
    seasons: List<Season>?,
    onSeasonClick: (Season) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color(0xFFf0eeed))
            .padding(start = 8.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            leagueIcon?.loadImageWithUrl(36.dp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = leagueName?.uppercase(Locale.ENGLISH) ?: "",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        SeasonDropdownMenu(seasons) {
            onSeasonClick.invoke(it)
        }
        Spacer(modifier = Modifier.height(8.dp))
    }

}

@Composable
fun StandingsInfo(
    standings: Standings?,
    headers: MutableList<String>,
    onTeamClickNotifier: (Standing?) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Header(headers)
        TeamList(standings, onTeamClickNotifier)
    }
}

@Composable
fun TeamList(standings: Standings?, onTeamClickNotifier: (Standing?) -> Unit) {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        this.itemsIndexed(items = standings?.standings!!) { index, item ->
            Team(
                item = item,
                bgColor = if (index % 2 != 0) Color(0xFFf0eeed) else Color.White,
                onTeamClickNotifier = onTeamClickNotifier
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Qualifications(standings.qualifications?.list)
        }
    }
}

@Composable
fun Team(item: Standing, bgColor: Color, onTeamClickNotifier: (Standing?) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(bgColor)
            .clickable {
                onTeamClickNotifier.invoke(item)
            },
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Rank(item.note)
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = item.rank ?: "",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        item.logo?.loadImageWithUrl()
        Text(
            text = item.teamName ?: "",
            fontSize = 12.sp,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 4.dp)
                .weight(4f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.width(16.dp))
        TeamStatistics(values = item.values)
        Spacer(modifier = Modifier.width(4.dp))
    }
    HorizontalDivider()
}

@Composable
fun Rank(note: Note?) {
    if (note?.color != null) {
        Box(
            modifier = Modifier
                .width(3.dp)
                .fillMaxHeight()
                .background(note.color?.hexColor ?: Color.Transparent)
        )
        Spacer(modifier = Modifier.width(1.dp))
    } else {
        Spacer(modifier = Modifier.width(4.dp))
    }
}