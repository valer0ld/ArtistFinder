package com.valer0ld.artistfinder.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.valer0ld.artistfinder.navigation.BottomNavigationBar
import com.valer0ld.artistfinder.navigation.NavRoutes
import com.valer0ld.artistfinder.ui.screens.game.GameViewModel
import com.valer0ld.artistfinder.ui.screens.game.components.GameBottomBar
import com.valer0ld.artistfinder.ui.screens.win_game.components.WinGameBar

@Composable
fun ScaffoldBottomBar(
    currentDestination: String?,
    selectedIndex: Int,
    gameViewModel: GameViewModel,
    onIconClick: (Int)->Unit,
    onHomeClick: ()->Unit
){
    when (currentDestination) {
        NavRoutes.Game.route -> GameBottomBar(
            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
            viewModel = gameViewModel,
            onHomeClick = onHomeClick
        )
        NavRoutes.WinGame.route -> WinGameBar(
            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
            onHomeClick = onHomeClick
        )
        else -> BottomNavigationBar(
            selectedIndex = selectedIndex,
            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
            onIconClick = onIconClick
        )
    }
}