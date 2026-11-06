package com.valer0ld.artistfinder.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valer0ld.artistfinder.presentation.screens.home.components.DailyGameBox
import com.valer0ld.artistfinder.presentation.screens.home.components.GameBuilderBox
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import com.valer0ld.artistfinder.presentation.viewmodel.HomeViewModel

@Composable
fun Home(innerPadding: PaddingValues, onGameClick: ()->Unit, viewModel: HomeViewModel = hiltViewModel()){
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .pointerInput(Unit){
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
            .padding(bottom = innerPadding.calculateBottomPadding())
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ){
        DailyGameBox(
            state.dailyStartArtist.name,
            state.dailyEndArtist.name,
            onGameClick = onGameClick
        )
        Text(
            "Или создайте свою игру",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(40.dp)
        )
        GameBuilderBox(
            state,
            onStartValueChanged = viewModel::onStartValueChanged,
            onEndValueChanged = viewModel::onEndValueChanged,
            onGameClick = onGameClick
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    AppTheme{
        Home(PaddingValues(), {})
    }
}
