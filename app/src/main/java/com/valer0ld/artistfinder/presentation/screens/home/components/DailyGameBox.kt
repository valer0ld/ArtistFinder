package com.valer0ld.artistfinder.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.presentation.components.FromArtistToArtist
import com.valer0ld.artistfinder.presentation.model.TextPreset


@Composable
fun DailyGameBox(start: String = "Воскресенье", finish: String = "Земфира", onGameClick: () -> Unit){
    val typography = MaterialTheme.typography
    val colorScheme = MaterialTheme.colorScheme
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(20.dp), false)
            .clip(RoundedCornerShape(20.dp))
            .background(colorScheme.primaryContainer)
            .padding(10.dp)
    ){
        Text(
            "Игра дня!",
            style = typography.headlineMedium,
            color = colorScheme.onPrimaryContainer,
            modifier = Modifier)

        FromArtistToArtist(
            TextPreset.titleL(start, MaterialTheme.colorScheme.onPrimaryContainer),
            TextPreset.titleL(finish, MaterialTheme.colorScheme.onPrimaryContainer),
            modifier = Modifier
        )

        Button(
            onClick = onGameClick,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 1.dp,
            )
        ) {
            Icon(
                painter = painterResource((R.drawable.play_arrow_24px)),
                contentDescription = "Play",
                modifier = Modifier.size(30.dp)
            )
            Text("Играть", style = typography.titleLarge)
        }
    }
}
