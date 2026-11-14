package com.valer0ld.artistfinder.presentation.screens.win_game

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.presentation.components.AnimatedStepsList
import com.valer0ld.artistfinder.presentation.components.FromArtistToArtist
import com.valer0ld.artistfinder.presentation.model.TextPreset
import com.valer0ld.artistfinder.presentation.screens.win_game.components.StatisticContainer
import com.valer0ld.artistfinder.presentation.screens.win_game.components.StepsHeader
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import com.valer0ld.artistfinder.presentation.viewmodel.WinViewModel

@Composable
fun WinGameScreen(viewModel: WinViewModel = hiltViewModel()){
    var expanded by remember{ mutableStateOf(false) }
    val listState = rememberLazyListState()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(expanded) {
        if (!expanded) {
            listState.animateScrollBy(
                value = 0f,
                animationSpec = tween(
                    durationMillis = 600,
                    easing = FastOutSlowInEasing
                )
            )
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp,top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        userScrollEnabled = expanded
    ){
        item{
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Icon(
                    painter = painterResource(R.drawable.trophy_24px),
                    contentDescription = "win trophy",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(70.dp)
                )
                Text(
                    text = "Победа!",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(Modifier.height(50.dp))

            FromArtistToArtist(
                TextPreset.body(viewModel.getStartArtist().name),
                TextPreset.body(viewModel.getTargetArtist().name))
        }

        item{
            StatisticContainer(state)
        }

        stickyHeader {
            StepsHeader(
                expanded = expanded,
                onToggle = {
                    expanded = !expanded
                }
            )
        }

        item {
            AnimatedStepsList(expanded, state.steps)
        }

        item{
            Spacer(Modifier.height(100.dp))
        }
    }
}


@Preview
@Composable
fun WinGameScreenPreview(){
    AppTheme{
        WinGameScreen()
    }
}