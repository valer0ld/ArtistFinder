package com.valer0ld.artistfinder.presentation.screens.user_stats.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.domain.model.UserStatistic
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import com.valer0ld.artistfinder.presentation.theme.extendedColors
import com.valer0ld.artistfinder.presentation.util.formatTotalTime

@Composable
fun StatPanel(userStat: UserStatistic){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatPanelTable(userStat, modifier = Modifier.weight(5f))

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .weight(1f)
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    text = "Лучший рейтинг:"
                )
            }
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    text = userStat.bestRating.toString()
                )
            }
        }
    }

}

@Composable
fun StatPanelTable(userStat: UserStatistic, modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        StatTextColumn(
            textList = listOf(
                "",
                "Начато игр:",
                "Выиграно игр:",
                "Общее время:",
                "Сделано шагов:"
            ),
            textColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )
        StatTextColumn(
            textList = listOf(
                "Общая",
                userStat.gameStarts.toString(),
                userStat.gameEnds.toString(),
                formatTotalTime(userStat.totalTime),
                userStat.totalSteps.toString()
            ),
            modifier = Modifier.weight(1f)
        )
        StatTextColumn(
            textList = listOf(
                "Игры дня",
                userStat.dailyGameStarts.toString(),
                userStat.dailyGameEnds.toString(),
                formatTotalTime(userStat.dailyTime),
                userStat.dailySteps.toString()
            ),
            containerColor = MaterialTheme.extendedColors.gold,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun StatTextColumn(
    textList: List<String>,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    textColor: Color = MaterialTheme.colorScheme.tertiary,
    containerColor: Color = Color.Transparent,
){
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(
                if (containerColor == Color.Transparent) Color.Transparent
                else containerColor.copy(alpha = 0.5f)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        textList.forEach { text ->
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    textAlign = TextAlign.Center,
                    style = textStyle,
                    color = textColor,
                    text = text
                )
            }
        }
    }
}

@Preview
@Composable
fun GameListPreview(){
    AppTheme{
        StatPanel(UserStatistic(
            gameStarts = 0,
            gameEnds = 0,
            totalTime = 0,
            totalSteps = 0,
            dailyGameStarts = 0,
            dailyGameEnds = 0,
            dailyTime = 0,
            dailySteps = 0,
            bestRating = 0
        ))
    }
}
