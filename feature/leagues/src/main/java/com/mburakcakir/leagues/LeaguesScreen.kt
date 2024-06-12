package com.mburakcakir.leagues

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mburakcakir.common.extensions.loadImageWithUrl
import com.mburakcakir.model.leagues.League
import com.mburakcakir.ui.loading.Loading


@Composable
fun LeaguesScreen(
    leaguesUIState: LeaguesUIState,
    onNavigateStandings: (String, String) -> Unit
) {
    if(leaguesUIState.isLoading) {
        Loading()
    } else {
        Column {
            LeaguesList(leaguesUIState.leagues?.leagues, onNavigateStandings)
        }
    }

}

@Composable
fun LeaguesList(leagues: List<League>?, onNavigateStandings: (String, String) -> Unit) {
    if (leagues?.isNotEmpty() == true) {
        LazyColumn(modifier = Modifier.padding(horizontal = 4.dp)) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
            }
            this.items(items = leagues) { item ->
                LeagueItem(
                    league = item,
                    onNavigateStandings = onNavigateStandings
                )
                HorizontalDivider()
            }

        }
    } else {
        // TODO: Add common message
    }
}

@Composable
fun LeagueItem(league: League, onNavigateStandings: (String, String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigateStandings.invoke(league.id, league.logos.light)
            }
            .padding(8.dp)
    ) {
        league.logos.light.loadImageWithUrl(24.dp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = league.name,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}