package com.valer0ld.artistfinder.ui.screens.rating

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.ui.models.TemporaryPlayerList
import com.valer0ld.artistfinder.ui.screens.rating.components.CurrentPlayerRecordAndStats
import com.valer0ld.artistfinder.ui.screens.rating.components.PlayerRecordAndStats
import com.valer0ld.artistfinder.ui.screens.rating.components.TableHeader
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun RatingScreen(innerPadding: PaddingValues){
    Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Рейтинг по играм дня!",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            textAlign = TextAlign.Center
        )
        RatingTable()
    }
}

@Composable
fun RatingTable(){
    val sortedPlayer = TemporaryPlayerList.players.sortedBy { it.place }
    var selectedIndex by remember { mutableIntStateOf(-1) }
    val currentPlayer = TemporaryPlayerList.currentPlayer

    Surface(
        modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth(),
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
            Box{
                LazyColumn(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .fillMaxWidth()
                        .height(550.dp)
                ) {
                    itemsIndexed(
                        items = sortedPlayer,
                        key = { _, item -> item.id}
                    ){ index, player ->
                        val isExpanded = selectedIndex == player.id

                        PlayerRecordAndStats(
                            player = player,
                            currentPlayerId = currentPlayer.id,
                            index = index + 1,
                            isExpanded = isExpanded
                        ){
                                selectedIndex =  if (isExpanded) -1 else player.id
                            }
                    }
                    item{
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
                CurrentPlayerRecordAndStats(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    currentPlayer = currentPlayer)
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun PlayerRecordPreview(){
    AppTheme{
        RatingScreen(PaddingValues())
    }
}