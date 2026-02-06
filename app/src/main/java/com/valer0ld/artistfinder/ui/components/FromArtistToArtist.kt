package com.valer0ld.artistfinder.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.ui.models.TextConfig
import com.valer0ld.artistfinder.ui.models.TextPreset

@Composable
fun FromArtistToArtist(startArtist: TextConfig, finishArtist: TextConfig, modifier: Modifier = Modifier){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TitleAndContentText(
            TextPreset.bodyS("Старт", MaterialTheme.colorScheme.primary),
            startArtist
        )
        Icon(
            painter = painterResource(R.drawable.line_end_arrow_notch_24px),
            contentDescription = "Стрелка",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(40.dp)
        )
        TitleAndContentText(
            TextPreset.bodyS("Цель", MaterialTheme.colorScheme.primary),
            finishArtist
        )
    }
}