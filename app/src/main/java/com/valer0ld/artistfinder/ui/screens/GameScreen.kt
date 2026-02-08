package com.valer0ld.artistfinder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.valer0ld.artistfinder.ui.components.TitleAndContentText
import com.valer0ld.artistfinder.ui.models.TextPreset
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun GameScreen(navController: NavController, currentTurn: Int, incrementTurn: ()->Unit){
    val artists = arrayOf("Воскресенье", "Земфира", "Аквариум", "ДДТ", "ЧИЖ")
    val currentArtist = remember { mutableStateOf(artists[0]) }
    val targetArtist = "ЧИЖ"

    val colorScheme = MaterialTheme.colorScheme

    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .windowInsetsPadding(WindowInsets.statusBars)
        ) {
            TitleAndContentText(
                TextPreset.body("Цель",
                colorScheme.outlineVariant),
                TextPreset.display(targetArtist,
                colorScheme.primary)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                TitleAndContentText(
                    TextPreset.bodyM("Время", colorScheme.outlineVariant),
                    TextPreset.displayS("00:00", colorScheme.tertiary)
                )
                TitleAndContentText(
                    TextPreset.bodyM("Текущий шаг", colorScheme.outlineVariant),
                    TextPreset.displayS(currentTurn.toString(), colorScheme.tertiary)
                )
            }
            MainGamePanel(artists, currentArtist.value) { newArtist ->
                if (newArtist == targetArtist){
                    navController.navigate("win") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                    }
                }
                currentArtist.value = newArtist
                incrementTurn()
            }
        }
}

@Composable
fun NextArtistButton(artist: String, modifier: Modifier = Modifier, onArtistClick: (String)->Unit){
    Box(
        Modifier
            .fillMaxHeight()
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .clickable { onArtistClick(artist) }
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(text = artist, style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary)
    }
}

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

@Preview
@Composable
fun GameScreenPreview(){
    AppTheme{
        GameScreen(navController = rememberNavController(), 1, {})
    }
}