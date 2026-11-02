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
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.presentation.components.TitleAndContentText
import com.valer0ld.artistfinder.presentation.model.TextPreset


@Composable
fun StatisticContainer(){
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
            TextPreset.body("Время", MaterialTheme.colorScheme.primaryContainer),
            TextPreset.display("00:00", MaterialTheme.colorScheme.primary)
        )
        TitleAndContentText(
            TextPreset.body("Шаги", MaterialTheme.colorScheme.primaryContainer),
            TextPreset.display("0", MaterialTheme.colorScheme.primary)
        )
    }
    Spacer(Modifier.height(60.dp))
}
