package com.valer0ld.artistfinder.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.shadow
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
fun Home(navController: NavController, innerPadding: PaddingValues){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(bottom = innerPadding.calculateBottomPadding())
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(20.dp), false)
            .clip(RoundedCornerShape(20.dp))
            .background(colorScheme.tertiaryContainer)
            .padding(10.dp)
    ){
        Text(
            "Игра дня!",
            style = typography.headlineMedium,
            color = colorScheme.onTertiaryContainer,
            modifier = Modifier)

        FromArtistToArtist(
            TextPreset.titleL(start, MaterialTheme.colorScheme.onTertiaryContainer),
            TextPreset.titleL(finish, MaterialTheme.colorScheme.onTertiaryContainer),
            modifier = Modifier
        )

        Button(onClick = {
            navController.navigate("game") {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true
            }},
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

@Composable
fun GameBuilderBox(navController: NavController){
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .shadow(3.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(15.dp)
    ) {
        CustomTextField("Старт", Modifier)
        CustomTextField("Цель", Modifier)
        Row(Modifier){
            Button(
                onClick ={
                    navController.navigate("game") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                shape = RoundedCornerShape(20.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 1.dp,
                ),
                modifier = Modifier
                    .padding(end = 5.dp)
                    .weight(3f),
            ) {
                Text(
                    text = "Начать",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleLarge)
            }
            Button(
                onClick = {},
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(20.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 1.dp,
                )
            ){
                Icon(
                    painter = painterResource((R.drawable.shuffle_24px)),
                    contentDescription = "Play",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Composable
fun CustomTextField(placeholder: String, modifier: Modifier = Modifier){
    val message = remember { mutableStateOf("") }
    OutlinedTextField(
        value = message.value,
        onValueChange = {message.value = it},
        textStyle = MaterialTheme.typography.titleMedium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.outlineVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceBright
        ),
        modifier = Modifier.fillMaxWidth().then(modifier),
        placeholder = {Text(placeholder, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.outlineVariant)}
    )
}

@Preview
@Composable
fun HomePreview() {
    AppTheme{
        Home(navController = rememberNavController(), PaddingValues())
    }
}
