package com.mburakcakir.ui.seasondropdown

import android.util.Log
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
    onSeasonClick: (Season) -> Unit
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
            Log.v("selectedIndex", selectedIndex.toString())
            Text(
                seasons?.get(selectedIndex)?.displayName?.split(" ")?.get(0) ?: "",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentWidth()
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
                        onSeasonClick.invoke(seasons[index])
                    }
                )
            }
        }
    }
}
