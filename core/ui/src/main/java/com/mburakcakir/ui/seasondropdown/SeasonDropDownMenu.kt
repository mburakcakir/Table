package com.mburakcakir.ui.seasondropdown

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.text.font.FontWeight
import com.mburakcakir.common.extensions.clickableWithNoRippleEffect
import com.mburakcakir.network.model.Season

@Composable
fun SeasonDropdownMenu(
    seasons: List<Season>?,
    selectedSeason: Season? = null,
    onSeasonClick: (Season) -> Unit
) {
    val initialIndex = seasons?.indexOf(selectedSeason).takeIf { it != -1 } ?: 0

    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(initialIndex) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier.clickableWithNoRippleEffect { expanded = true },
            verticalAlignment = Alignment.CenterVertically
        ) {
            val selectedSeasonName =
                seasons?.getOrNull(selectedIndex)?.displayName?.split(" ")?.firstOrNull().orEmpty()

            Text(
                text = selectedSeasonName,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.wrapContentWidth()
            )
            Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "Arrow")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            seasons?.forEachIndexed { index, item ->
                val teamName = item.displayName?.split(" ")?.firstOrNull().orEmpty()
                DropdownMenuItem(
                    text = { Text(teamName) },
                    onClick = {
                        expanded = false
                        selectedIndex = index
                        onSeasonClick.invoke(seasons[index])
                    }
                )
            }
        }
    }
}