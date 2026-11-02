package com.valer0ld.artistfinder.presentation.screens.user_stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.valer0ld.artistfinder.presentation.components.ChildrenScreen
import com.valer0ld.artistfinder.presentation.model.DanilaStats
import com.valer0ld.artistfinder.presentation.screens.user_stats.components.StatPanel
import com.valer0ld.artistfinder.presentation.theme.AppTheme

@Composable
fun StatisticScreen(innerPadding: PaddingValues, onBackArrow: ()->Unit){
    ChildrenScreen(onBackArrow) {
        Column(
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding())
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ){
            StatPanel(DanilaStats.stats)
        }
    }
}

@Composable
@Preview
fun StatisticScreenPreview(){
    AppTheme{
        StatisticScreen(PaddingValues()) { }
    }
}