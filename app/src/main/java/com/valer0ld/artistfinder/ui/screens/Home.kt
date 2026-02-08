package com.valer0ld.artistfinder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.ui.components.FromArtistToArtist
import com.valer0ld.artistfinder.ui.models.TextPreset
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun Home(navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(20.dp)
    ){
        DailyGameBox(navController = navController)
        Text(
            "Или создайте свою игру",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(40.dp))
        GameBuilderBox(navController)
    }
}

@Composable
fun DailyGameBox(start: String = "Воскресенье", finish: String = "Земфира", navController: NavController){
    val typography = MaterialTheme.typography
    val colorScheme = MaterialTheme.colorScheme
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(colorScheme.tertiaryContainer)
            .border(width = 1.dp,color = colorScheme.tertiary, RoundedCornerShape(10.dp))
            .padding(10.dp)
    ){
        Text(
            "Игра дня!",
            style = typography.headlineSmall,
            color = colorScheme.onTertiaryContainer,
            modifier = Modifier.weight(1f))

        FromArtistToArtist(
            TextPreset.body(start, MaterialTheme.colorScheme.onTertiaryContainer),
            TextPreset.body(finish, MaterialTheme.colorScheme.onTertiaryContainer),
            modifier = Modifier.weight(3f)
        )

        Button(onClick = {
            navController.navigate("game") {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true
            }},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
            ) {
            Icon(
                painter = painterResource((R.drawable.play_arrow_24px)),
                contentDescription = "Play",
                modifier = Modifier.size(30.dp)
            )
            Text("Играть", style = typography.bodyLarge)
        }
    }
}

@Composable
fun GameBuilderBox(navController: NavController){
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(1.dp, MaterialTheme.colorScheme.onSurface,RoundedCornerShape(10.dp))
            .padding(5.dp)
    ) {
        CustomTextField("Старт", Modifier)
        CustomTextField("Цель", Modifier)
        Row(Modifier.padding(horizontal = 10.dp)){
            Button(
                onClick ={
                    navController.navigate("game") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(end = 5.dp)
                    .weight(3f),
            ) {
                Text("Начать", color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.bodyLarge)
            }
            Button(
                onClick = {},
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp)
            ){
                Icon(
                    painter = painterResource((R.drawable.shuffle_24px)),
                    contentDescription = "Play",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun CustomTextField(placeholder: String, modifier: Modifier = Modifier){
    val message = remember { mutableStateOf("") }
    OutlinedTextField(
        message.value,
        onValueChange = {message.value = it},
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceBright).then(modifier),
        placeholder = {Text(placeholder, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.outlineVariant)}
    )
}

@Preview
@Composable
fun HomePreview() {
    AppTheme(){
        Home(navController = rememberNavController())
    }
}
