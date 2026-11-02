package com.valer0ld.artistfinder.presentation.screens.rating.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.valer0ld.artistfinder.presentation.components.ExpandedAnimatedComponent
import com.valer0ld.artistfinder.domain.model.RatingPlayer
import com.valer0ld.artistfinder.presentation.theme.extendedColors


@Composable
fun PlayerRecordAndStats(
    player: RatingPlayer,
    currentPlayerId: Int,
    index: Int,
    isExpanded: Boolean,
    onRecordClick: ()->Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (player.id == currentPlayerId) {
                    Modifier.border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                    )
                }
                else Modifier
            )
    ){
        PlayerRecord(player, index, onRecordClick)
    }
    ExpandedPlayerStats(isExpanded, player)
    HorizontalDivider(color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.7f))
}

@Composable
fun PlayerRecord(player: RatingPlayer, index: Int, onPlayerClick: ()->Unit){
    TableItem(
        modifier = Modifier
            .clickable { onPlayerClick() }
            .background( when (index){
                1 -> MaterialTheme.extendedColors.gold.copy(0.5f)
                2 -> MaterialTheme.extendedColors.silver.copy(0.5f)
                3 -> MaterialTheme.extendedColors.bronze.copy(0.5f)
                else -> Color.Transparent
            })
    ){
        RatingText(index.toString(), modifier = Modifier.weight(1f))
        VerticalDivider(color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
        AsyncImage(
            model = player.picture,
            contentDescription = "default profile picture",
            modifier = Modifier.size(35.dp).weight(1.5f),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        RatingText(player.name, modifier = Modifier.weight(3.5f))
        VerticalDivider(color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
        RatingText(player.sumScore.toString(), modifier = Modifier.weight(2f))
    }
}

@Composable
fun ExpandedPlayerStats(expand: Boolean, player: RatingPlayer){
    ExpandedAnimatedComponent(expand) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.tertiaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TableItem{
                RatingText("Всего Игр", modifier = Modifier.weight(1f), color =  MaterialTheme.colorScheme.onPrimaryContainer)
                RatingText("Всего Шагов", modifier = Modifier.weight(1f), color =  MaterialTheme.colorScheme.onPrimaryContainer)
                RatingText("Общее время", modifier = Modifier.weight(1f), color =  MaterialTheme.colorScheme.onPrimaryContainer)
            }
            TableItem{
                RatingText(player.dailyGames.toString(), modifier = Modifier.weight(1f), color =  MaterialTheme.colorScheme.onPrimaryContainer)
                RatingText(player.totalSteps.toString(), modifier = Modifier.weight(1f), color =  MaterialTheme.colorScheme.onPrimaryContainer)
                RatingText(player.totalTime, modifier = Modifier.weight(1f), color =  MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }
    }
}
