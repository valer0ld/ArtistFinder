package com.valer0ld.artistfinder.presentation.screens.game.components

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.domain.model.Artist
import com.valer0ld.artistfinder.presentation.viewmodel.GameUIState
import com.valer0ld.artistfinder.presentation.viewmodel.GameViewModel


@Composable
fun MainGamePanel(state: GameUIState, onArtistButtonClick: (Int, Artist, ()->Unit)->Unit, onWinGame: ()->Unit){

    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .height(450.dp)
        ) {
            var t = 0
            for (i in 0..1) {
                Row(Modifier.weight(1f).fillMaxWidth().padding(start = 6.dp, end = 6.dp)) {
                    for (y in 0..1) {
                        if (state.similarArtists[t] == state.currentArtist) t++

                        val buttonId = (y + i * 2)
                        val buttonArtist = state.similarArtists[t]

                        val isProcessing = state.processingButtonId == buttonId

                        NextArtistButton(
                            buttonArtist,
                            top = buttonId < 2,
                            Modifier.weight(1f),
                            isProcessing,
                            onArtistClick = {
                                onArtistButtonClick(
                                    buttonId,
                                    buttonArtist,
                                    onWinGame
                                )
                            }
                        )
                        t++
                    }
                }
            }
        }
        Box(
            Modifier
                .size(130.dp)
                .clip(shape = RoundedCornerShape(60.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(2.dp, MaterialTheme.colorScheme.onPrimaryContainer, RoundedCornerShape(60.dp))
                .clickable{},
            contentAlignment = Alignment.Center,
        ) {
            Text(
                state.currentArtist.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

    }
}
