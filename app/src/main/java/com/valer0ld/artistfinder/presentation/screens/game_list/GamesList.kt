package com.valer0ld.artistfinder.presentation.screens.game_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.valer0ld.artistfinder.presentation.components.AnimatedStepsList
import com.valer0ld.artistfinder.presentation.components.ChildrenScreen
import com.valer0ld.artistfinder.domain.model.TempListOfGames
import com.valer0ld.artistfinder.presentation.screens.game_list.components.GameItem

@Composable
fun GamesList(onGoToGame: ()->Unit, onBackArrow: ()->Unit){
    var selectedIndex by remember { mutableIntStateOf(-1) }

    ChildrenScreen(onBackArrow) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(
                items = TempListOfGames.list,
                key = {it.id}
            ){ game ->
                val isExpanded = selectedIndex == game.id

                key(game.id){
                    GameItem(
                        game = game,
                        onPanelClick = {
                            selectedIndex =  if (isExpanded) -1 else game.id
                        },
                        onPlayClick = onGoToGame
                    )
                    Spacer(Modifier.height(5.dp))
                    AnimatedStepsList(isExpanded, game.steps)
                    Spacer(Modifier.height(5.dp))
                }

            }

            item{
                Spacer(Modifier.height(100.dp))
            }
        }
    }
}

@Preview
@Composable
fun GameListPreview(){
    AppTheme{
        GamesList({}, {})
    }
}
