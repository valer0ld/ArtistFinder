package com.valer0ld.artistfinder.presentation.screens.game

import android.os.SystemClock
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valer0ld.artistfinder.presentation.components.TitleAndContentText
import com.valer0ld.artistfinder.presentation.model.TextPreset
import com.valer0ld.artistfinder.presentation.screens.game.components.MainGamePanel
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import com.valer0ld.artistfinder.presentation.util.formatDuration
import com.valer0ld.artistfinder.presentation.viewmodel.GameViewModel
import kotlinx.coroutines.delay

@Composable
fun GameScreen(innerPadding: PaddingValues, viewModel: GameViewModel = hiltViewModel(), onWinGame: ()->Unit){
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val colorScheme = MaterialTheme.colorScheme

    var duration by remember { mutableLongStateOf(0L) }
    LaunchedEffect(state.startTime) {
        while (true) {
            duration = SystemClock.elapsedRealtime() - state.startTime
            delay(1000)
        }
    }

    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding())
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .windowInsetsPadding(WindowInsets.statusBars)
        ) {
            TitleAndContentText(
                TextPreset.body("Цель",
                colorScheme.outlineVariant),
                TextPreset.display(state.targetArtist.name,
                colorScheme.primary)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                TitleAndContentText(
                    TextPreset.bodyM("Время", colorScheme.outlineVariant),
                    TextPreset.displayS(formatDuration(duration), colorScheme.tertiary)
                )
                TitleAndContentText(
                    TextPreset.bodyM("Текущий шаг", colorScheme.outlineVariant),
                    TextPreset.displayS(state.currentStep.toString(), colorScheme.tertiary)
                )
            }
            MainGamePanel(state, viewModel::onArtistButtonClick, onWinGame)
        }
}

@Preview
@Composable
fun GameScreenPreview(){
    AppTheme{
        GameScreen(PaddingValues(), viewModel(), {})
    }
}