package com.valer0ld.artistfinder.ui.screens.home

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.ui.screens.home.components.DailyGameBox
import com.valer0ld.artistfinder.ui.screens.home.components.GameBuilderBox
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun Home(innerPadding: PaddingValues, onGameClick: ()->Unit){
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
        DailyGameBox( onGameClick = onGameClick)
        Text(
            "Или создайте свою игру",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(40.dp))
        GameBuilderBox(onGameClick = onGameClick)
    }
}

@Preview
@Composable
fun HomePreview() {
    AppTheme{
        Home(PaddingValues(), {})
    }
}
