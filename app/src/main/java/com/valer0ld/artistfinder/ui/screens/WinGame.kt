package com.valer0ld.artistfinder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.ui.components.FromArtistToArtist
import com.valer0ld.artistfinder.ui.components.TitleAndContentText
import com.valer0ld.artistfinder.ui.models.TextPreset
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun WinGame(){
    val expanded = remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,

    ){

        item{
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Icon(
                    painter = painterResource(R.drawable.trophy_24px),
                    contentDescription = "win trophy",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = "Победа!",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(Modifier.height(50.dp))

            FromArtistToArtist(
                TextPreset.body("Воскресенье"),
                TextPreset.body("Земфира"))
        }
        item{
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
        }
        item{

        }
    }
}



@Preview
@Composable
fun WinGamePreview(){
    AppTheme{
        WinGame()
    }
}