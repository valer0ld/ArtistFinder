package com.valer0ld.artistfinder.presentation.screens.game_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valer0ld.artistfinder.presentation.components.AnimatedStepsList
import com.valer0ld.artistfinder.presentation.components.ChildrenScreen
import com.valer0ld.artistfinder.presentation.components.LazyColumnWithScrollbar
import com.valer0ld.artistfinder.presentation.screens.game_list.components.GameItem
import com.valer0ld.artistfinder.presentation.viewmodel.GameListViewModel

@Composable
fun GamesList(onGoToGame: ()->Unit, onBackArrow: ()->Unit, viewModel: GameListViewModel = hiltViewModel()){
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    var selectedIndex by remember { mutableLongStateOf(-1) }

    ChildrenScreen(onBackArrow) {
        LazyColumnWithScrollbar(
            listState = listState,
            isExpanded = selectedIndex != -1L,
            modifier = Modifier
                .fillMaxSize()
            ) {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(start = 10.dp, end = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                items(
                    items = state.gameList,
                    key = {it.id}
                ){ game ->
                    val isExpanded = selectedIndex == game.id

                    key(game.id){
                        GameItem(
                            game = game,
                            onPanelClick = {
                                selectedIndex =  if (isExpanded) -1L else game.id
                            },
                            onPlayClick = {
                                viewModel.onGameStart(game.id){
                                    onGoToGame()
                                }
                            }
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
}

@Preview
@Composable
fun GameListPreview(){
    AppTheme{
        GamesList({}, {})
    }
}
