package com.valer0ld.artistfinder.presentation.screens.user_stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valer0ld.artistfinder.presentation.components.ChildrenScreen
import com.valer0ld.artistfinder.presentation.screens.user_stats.components.StatPanel
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import com.valer0ld.artistfinder.presentation.viewmodel.StatisticViewModel

@Composable
fun StatisticScreen(innerPadding: PaddingValues, onBackArrow: ()->Unit, viewModel: StatisticViewModel = hiltViewModel()){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ChildrenScreen(onBackArrow) {
        Column(
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding())
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ){
            StatPanel(state.userStats)
        }
    }
}

@Composable
@Preview
fun StatisticScreenPreview(){
    AppTheme{
        StatisticScreen(
            PaddingValues(),
            onBackArrow = { },
        )
    }
}