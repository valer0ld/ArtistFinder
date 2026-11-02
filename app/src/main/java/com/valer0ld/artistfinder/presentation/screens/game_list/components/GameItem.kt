package com.valer0ld.artistfinder.presentation.screens.game_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.presentation.components.BouncingIconButton
import com.valer0ld.artistfinder.domain.model.Game


@Composable
fun GameItem(game: Game, onPanelClick: ()->Unit, onPlayClick: ()->Unit){
    Surface(
        tonalElevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onPanelClick() }
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GameItemInfo(game)
            BouncingIconButton(
                icon = R.drawable.play_arrow_24px,
                label = "play",
                color = MaterialTheme.colorScheme.tertiary,
                size = 60.dp,
                onIconClick = onPlayClick
            )
        }
    }
}
