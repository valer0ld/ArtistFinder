package com.valer0ld.artistfinder.presentation.screens.game

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valer0ld.artistfinder.presentation.components.TitleAndContentText
import com.valer0ld.artistfinder.presentation.model.TextPreset
import com.valer0ld.artistfinder.presentation.screens.game.components.MainGamePanel
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import com.valer0ld.artistfinder.presentation.viewmodel.GameViewModel

@Composable
fun GameScreen(innerPadding: PaddingValues, viewModel: GameViewModel, onWinGame: ()->Unit){
    val colorScheme = MaterialTheme.colorScheme

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
                TextPreset.display(viewModel.targetArtist.name,
                colorScheme.primary)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                TitleAndContentText(
                    TextPreset.bodyM("Время", colorScheme.outlineVariant),
                    TextPreset.displayS("00:00", colorScheme.tertiary)
                )
                TitleAndContentText(
                    TextPreset.bodyM("Текущий шаг", colorScheme.outlineVariant),
                    TextPreset.displayS(viewModel.currentStep.collectAsState().value.toString(), colorScheme.tertiary)
                )
            }
            MainGamePanel(viewModel, onWinGame)
        }
}

@Preview
@Composable
fun GameScreenPreview(){
    AppTheme{
        GameScreen(PaddingValues(), viewModel(), {})
    }
}