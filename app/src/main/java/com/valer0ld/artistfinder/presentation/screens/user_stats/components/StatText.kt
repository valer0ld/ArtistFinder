package com.valer0ld.artistfinder.presentation.screens.user_stats.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.presentation.model.UserStatistic


@Composable
fun StatText(title: String, metrics: String){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            text = title,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primaryContainer,
            text = metrics
        )
    }
}

@Composable
fun StatPanel(userStat: UserStatistic){
    StatText("Начато игр:", userStat.gameStarts.toString())
    StatText("Закончено игр:", userStat.gameEnds.toString())
    StatText("Времени в игре:", userStat.totalTime.toString())
    StatText("Сделано шагов:", userStat.totalSteps.toString())
    StatText("Лучший рейтинг:", userStat.bestRating.toString())
}