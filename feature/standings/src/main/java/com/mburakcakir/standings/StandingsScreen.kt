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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mburakcakir.common.extensions.clickableWithNoRippleEffect
import com.mburakcakir.common.extensions.hexColor
import com.mburakcakir.common.extensions.isNotNullAndEmpty
import com.mburakcakir.common.extensions.loadImageWithUrl
import com.mburakcakir.common.extensions.notNullOrEmptyComposable
import com.mburakcakir.domain.model.Qualification
import com.mburakcakir.domain.model.Standing
import com.mburakcakir.domain.model.Standings
import com.mburakcakir.network.model.Note
import com.mburakcakir.network.model.Season
import com.mburakcakir.ui.Loading
import java.util.Locale

@Composable
fun StandingsScreen(
    uiState: StandingsUiState,
    onSeasonClick: (Int) -> Unit,
    onTeamClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        uiState.standings?.standings.notNullOrEmptyComposable {
            DropDownHeader(
                uiState.standings?.name,
                uiState.leagueIcon,
                uiState.seasons?.seasons,
                onSeasonClick
            )
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (uiState.standings?.standings.isNotNullAndEmpty() && !uiState.isLoading) {
                Standings(
                    standings = uiState.standings,
                    headers = uiState.headerList,
                    onTeamClick = onTeamClick
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
    onSeasonClick: (Int) -> Unit
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
fun Standings(standings: Standings?, headers: MutableList<String>, onTeamClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Header(headers)
        TeamList(standings, onTeamClick)
    }
}

@Composable
fun Header(headers: MutableList<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Spacer(modifier = Modifier.weight(1f))
        headers.forEach {
            HeaderDetail(shortcut = it)
        }
        Spacer(modifier = Modifier.width(4.dp))
    }
}

@Composable
fun HeaderDetail(shortcut: String) {
    Text(
        text = shortcut,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        maxLines = 1,
        modifier = Modifier.width(24.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun TeamList(standings: Standings?, onTeamClick: () -> Unit) {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        this.itemsIndexed(items = standings?.standings!!) { index, item ->
            Team(
                item = item,
                bgColor = if (index % 2 != 0) Color(0xFFf0eeed) else Color.White,
                onTeamClick = onTeamClick
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            QualificationsInfo(standings.qualifications?.list)
        }
    }
}

@Composable
fun QualificationsInfo(qualifications: List<Qualification>?) {
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

@Composable
fun Team(item: Standing, bgColor: Color, onTeamClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(bgColor)
            .clickable {
                onTeamClick()
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
        item.values?.forEachIndexed { index, it ->
            if (index == (item.values?.size?.minus(1) ?: 0)) {
                TeamStatistics(info = it, fontWeight = FontWeight.Bold)
            } else {
                TeamStatistics(info = it)
            }
        }
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

@Composable
fun TeamStatistics(info: String, fontWeight: FontWeight = FontWeight.Normal) {
    Text(
        text = info,
        fontWeight = fontWeight,
        fontSize = 12.sp,
        maxLines = 1,
        modifier = Modifier.width(24.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun SeasonDropdownMenu(
    seasons: List<Season>?,
    onSeasonClick: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier.clickableWithNoRippleEffect { expanded = true },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                seasons?.get(selectedIndex)?.displayName?.split(" ")?.get(0) ?: "",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(4.dp)
            )
            Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "Arrow")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            seasons?.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { Text(item.displayName.split(" ")[0]) },
                    onClick = {
                        expanded = false
                        selectedIndex = index
                        onSeasonClick.invoke(seasons[index].year)
                    }
                )
            }
        }
    }
}
