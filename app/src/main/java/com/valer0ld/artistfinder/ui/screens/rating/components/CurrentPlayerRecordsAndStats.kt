package com.valer0ld.artistfinder.ui.screens.rating.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.ui.models.RatingPlayer


@Composable
fun CurrentPlayerRecordAndStats(modifier: Modifier = Modifier, currentPlayer: RatingPlayer){
    var currentIsExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(width = 2.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = RoundedCornerShape(
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                )
            )
    ){
        PlayerRecord(currentPlayer, currentPlayer.place) { currentIsExpanded = !currentIsExpanded }
        ExpandedPlayerStats(currentIsExpanded, currentPlayer)
    }
}
