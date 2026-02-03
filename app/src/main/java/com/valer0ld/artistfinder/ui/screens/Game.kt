package com.valer0ld.artistfinder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun Game(){
    val artists = arrayOf("Воскресенье", "Земфира", "Аквариум", "ДДТ", "ЧИЖ")
    val currentArtist = remember { mutableStateOf(artists[0]) }
    val typography = MaterialTheme.typography
    val colorScheme = MaterialTheme.colorScheme
    Box(Modifier.fillMaxSize().background(colorScheme.onBackground), contentAlignment = Alignment.Center){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = currentArtist.value, style = typography.displayMedium,
                color = colorScheme.background)
            Box(contentAlignment = Alignment.Center) {
                Column() {
                    var t = 0
                    for (i in 0..1) {
                        Row(Modifier.padding(start = 6.dp, end = 6.dp)) {
                            for (y in 0..1) {
                                if (artists[t] == currentArtist.value) t++
                                val tmp = artists[t]
                                Box(
                                    Modifier
                                        .size(200.dp)
                                        .padding(2.dp)
                                        .clip(shape = RoundedCornerShape(10.dp))
                                        .background(colorScheme.primary)
                                        .clickable {
                                            currentArtist.value = tmp
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = artists[t], style = typography.headlineMedium,
                                        color = colorScheme.background)
                                    t++
                                }

                            }
                        }
                    }
                }
                Box(
                    Modifier
                        .size(130.dp)
                        .clip(shape = RoundedCornerShape(60.dp))
                        .background(colorScheme.tertiary)
                        .clickable{},
                    contentAlignment = Alignment.Center
                ) {
                    Text("Кнопка", style = typography.headlineSmall,
                        color = colorScheme.background)
                }

            }
        }
    }
}