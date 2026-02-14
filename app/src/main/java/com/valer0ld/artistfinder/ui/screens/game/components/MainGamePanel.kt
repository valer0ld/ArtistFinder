package com.valer0ld.artistfinder.ui.screens.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp


@Composable
fun MainGamePanel(artists: Array<String>, currentArtist: String, onArtistClick: (String) -> Unit){
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .height(400.dp)
        ) {
            var t = 0
            for (i in 0..1) {
                Row(Modifier.weight(1f).fillMaxWidth().padding(start = 6.dp, end = 6.dp)) {
                    for (y in 0..1) {
                        if (artists[t] == currentArtist) t++
                        NextArtistButton(artists[t], Modifier.weight(1f), onArtistClick = onArtistClick )
                        t++
                    }
                }
            }
        }
        Box(
            Modifier
                .size(130.dp)
                .clip(shape = RoundedCornerShape(60.dp))
                .background(MaterialTheme.colorScheme.primary)
                .border(2.dp, MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(60.dp))
                .clickable{},
            contentAlignment = Alignment.Center,
        ) {
            Text(
                currentArtist,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }

    }
}
