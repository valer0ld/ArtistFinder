package com.valer0ld.artistfinder.presentation.screens.game_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.domain.model.Game
import com.valer0ld.artistfinder.presentation.util.toSpanStyleColored
import com.valer0ld.artistfinder.presentation.theme.extendedColors
import com.valer0ld.artistfinder.presentation.util.formatDuration


@Composable
fun GameItemInfo(game: Game){
    Column(
        modifier = Modifier
            .padding(5.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {
                withStyle(style = MaterialTheme.typography.bodyLarge.toSpanStyleColored(color = MaterialTheme.colorScheme.primary)){
                    append(game.start.name)
                }
                withStyle(style = MaterialTheme.typography.headlineSmall.toSpanStyleColored()){
                    append(stringResource(R.string.from_to))
                }
                withStyle(style = MaterialTheme.typography.bodyLarge.toSpanStyleColored(color = MaterialTheme.colorScheme.primary)){
                    append(game.target.name)
                }
            }
        )
        Row{
            Text(
                color = MaterialTheme.colorScheme.inverseSurface,
                text = buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyleColored(color = MaterialTheme.colorScheme.tertiary)) {
                        append(stringResource(R.string.steps))
                    }
                    withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyleColored()) {
                        append(game.stepCounter.toString())
                    }
                },
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            Text(
                color = MaterialTheme.colorScheme.inverseSurface,
                text = buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyleColored(color = MaterialTheme.colorScheme.tertiary)) {
                        append(stringResource(R.string.show_time))
                    }
                    withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyleColored()) {
                        append(formatDuration(game.duration))
                    }
                },
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            if(game.isDaily){
                Text(
                    color = MaterialTheme.extendedColors.gold,
                    text = buildAnnotatedString {
                        withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyleColored()) {
                            append(stringResource(R.string.daily_star))
                        }
                        withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyleColored()) {
                            append(stringResource(R.string.daily_game))
                        }
                    },
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }
        }
    }
}
