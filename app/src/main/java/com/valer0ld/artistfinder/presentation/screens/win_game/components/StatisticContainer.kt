package com.valer0ld.artistfinder.presentation.screens.win_game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.presentation.components.TitleAndContentText
import com.valer0ld.artistfinder.presentation.model.TextPreset
import com.valer0ld.artistfinder.presentation.util.formatDuration
import com.valer0ld.artistfinder.presentation.viewmodel.WinUIState


@Composable
fun StatisticContainer(state: WinUIState){
    Spacer(Modifier.height(80.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TitleAndContentText(
            TextPreset.body(stringResource(R.string.show_time), MaterialTheme.colorScheme.primaryContainer),
            TextPreset.display(formatDuration(state.duration), MaterialTheme.colorScheme.primary)
        )
        TitleAndContentText(
            TextPreset.body(stringResource(R.string.steps), MaterialTheme.colorScheme.primaryContainer),
            TextPreset.display(state.totalStep.toString(), MaterialTheme.colorScheme.primary)
        )
    }
    Spacer(Modifier.height(60.dp))
}
