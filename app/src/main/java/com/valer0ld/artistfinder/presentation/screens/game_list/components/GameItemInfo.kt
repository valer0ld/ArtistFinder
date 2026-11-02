package com.valer0ld.artistfinder.presentation.screens.game_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.domain.model.Game
import com.valer0ld.artistfinder.presentation.model.toSpanStyleColored


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
                    append(game.start)
                }
                withStyle(style = MaterialTheme.typography.headlineMedium.toSpanStyleColored()){
                    append(" → ")
                }
                withStyle(style = MaterialTheme.typography.bodyLarge.toSpanStyleColored(color = MaterialTheme.colorScheme.primary)){
                    append(game.target)
                }
            }
        )
        Row{
            Text(
                color = MaterialTheme.colorScheme.inverseSurface,
                text = buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyleColored(color = MaterialTheme.colorScheme.tertiary)) {
                        append("Шагов: ")
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
                        append("Время: ")
                    }
                    withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyleColored()) {
                        append(game.time.toString())
                    }
                },
                modifier = Modifier.padding(horizontal = 5.dp)
            )
        }
    }
}
