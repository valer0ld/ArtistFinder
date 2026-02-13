package com.valer0ld.artistfinder.ui.screens.rating

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.ui.models.RatingPlayer
import com.valer0ld.artistfinder.ui.models.TemporaryPlayerList
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun RatingScreen(innerPadding: PaddingValues){
    Column(
        modifier = Modifier.padding(innerPadding).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Рейтинг по играм дня!",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.fillMaxWidth().padding(20.dp),
        )
        RatingTable()
    }
}

@Composable
fun RatingTable(){
    val sortedPlayer = TemporaryPlayerList.players.sortedBy { it.place }

    Surface(
        modifier = Modifier.padding(15.dp).fillMaxWidth(),
        shape = RoundedCornerShape(
            20.dp
        ),
        shadowElevation = 6.dp
    ){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ){
            TableHeader()
            HorizontalDivider(color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f))
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .fillMaxWidth()
                    .height(600.dp)
            ) {
                itemsIndexed(
                    items = sortedPlayer,
                    key = { _, item -> item.id}
                ){ index, player ->
                    PlayerRecord(player, index + 1)
                    HorizontalDivider(color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
                }
            }
        }
    }

}

@Composable
fun TableHeader(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        RatingHeaderText("№", modifier = Modifier.width(30.dp))
        VerticalDivider(color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f))
        RatingHeaderText("Имя", modifier = Modifier.width(130.dp))
        VerticalDivider(color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f))
        RatingHeaderText("Всего Игр", modifier = Modifier.width(45.dp))
        VerticalDivider(color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f))
        RatingHeaderText("Всего Шагов", modifier = Modifier.width(45.dp))
        VerticalDivider(color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f))
        RatingHeaderText("Общее время", modifier = Modifier.width(80.dp))
        VerticalDivider(color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f))
        RatingHeaderText("Всего очков", modifier = Modifier.width(80.dp))
    }
}

@Composable
fun PlayerRecord(player: RatingPlayer, index: Int){
    Row(
        modifier = Modifier.fillMaxWidth().height(40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        RatingText(index.toString(), modifier = Modifier.width(30.dp))
        VerticalDivider(color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
        RatingText(player.name, modifier = Modifier.width(130.dp))
        VerticalDivider(color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
        RatingText(player.dailyGames.toString(), modifier = Modifier.width(45.dp))
        VerticalDivider(color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
        RatingText(player.totalSteps.toString(), modifier = Modifier.width(45.dp))
        VerticalDivider(color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
        RatingText(player.totalTime, modifier = Modifier.width(80.dp))
        VerticalDivider(color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
        RatingText(player.sumScore.toString(), modifier = Modifier.width(80.dp))
    }
}

@Composable
fun RatingText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.onSurface
){
    Text(
        text = text,
        style = style,
        color = color,
        maxLines = 1,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )
}

@Composable
fun RatingHeaderText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.onPrimaryContainer
){
    Text(
        text = text,
        style = style,
        color = color,
        maxLines = 2,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun PlayerRecordPreview(){
    AppTheme(){
        RatingScreen(PaddingValues())
    }
}