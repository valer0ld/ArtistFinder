package com.valer0ld.artistfinder.presentation.screens.profile_main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.presentation.screens.profile_main.components.OptionButton
import com.valer0ld.artistfinder.presentation.screens.profile_main.components.UserProfileViewer
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import com.valer0ld.artistfinder.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileMain(
    innerPadding: PaddingValues,
    onGamesClick: ()->Unit,
    onSettingsClick: ()->Unit,
    onStatsClick: ()->Unit,
    viewModel: ProfileViewModel = hiltViewModel()){
    Column(
        modifier = Modifier
            .padding(bottom = innerPadding.calculateBottomPadding())
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ){
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        UserProfileViewer(state.userName, state.userPicture, state.rating)
        Spacer(modifier = Modifier.height(30.dp))
        OptionButton("Мои игры", R.drawable.list_24px) {
            onGamesClick()
        }
        Spacer(modifier = Modifier.height(20.dp))
        OptionButton("Настройки", R.drawable.settings_24px){
            onSettingsClick()
        }
        Spacer(modifier = Modifier.height(20.dp))
        OptionButton("Статистика", R.drawable.outline_show_chart_24){
            onStatsClick()
        }
    }
}


@Preview
@Composable
fun ProfileMainPreview(){
    AppTheme{
        ProfileMain(PaddingValues(),{},{}, {})
    }
}