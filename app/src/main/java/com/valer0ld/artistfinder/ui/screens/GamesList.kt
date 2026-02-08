package com.valer0ld.artistfinder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.ui.models.Game
import com.valer0ld.artistfinder.ui.models.toSpanStyleColored
import com.valer0ld.artistfinder.ui.theme.AppTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.valer0ld.artistfinder.ui.components.AnimatedStepsList

@Composable
fun GamesList(navController: NavController){
    var selectedIndex by remember { mutableIntStateOf(-1) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        stickyHeader {
            ReturnActionHeader({
                navController.navigate("profile/main") {
                    popUpTo("profile") {
                        inclusive = false
                    }
                    restoreState = true
                }
            })
        }
        items(
            items = TempListOfGames.list,
            key = {it.id}
        ){ game ->
            val isExpanded = selectedIndex == game.id

            key(game.id){
                GameItem(
                    game = game,
                    onPanelClick = {
                        selectedIndex =  if (isExpanded) -1 else game.id
                    },
                    onPlayClick = {
                        navController.navigate("game") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                //saveState = true
                            }
                            restoreState = true
                        }
                    })
                Spacer(Modifier.height(5.dp))
                AnimatedStepsList(isExpanded, game.steps)
                Spacer(Modifier.height(5.dp))
            }

        }
    }
}

@Composable
fun GameItem(game: Game, onPanelClick: ()->Unit, onPlayClick: ()->Unit){
    Surface(
        tonalElevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onPanelClick() }
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GameItemInfo(game)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "Играть",
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.bodyMedium
                )
                IconButton(
                    onClick = onPlayClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.play_arrow_24px),
                        contentDescription = "play",
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun GameItemInfo(game: Game){
    Column(
        modifier = Modifier
            .padding(5.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {
                withStyle(style = MaterialTheme.typography.bodyLarge.toSpanStyleColored(color = MaterialTheme.colorScheme.primary)){
                    append(game.start)
                }
                withStyle(style = MaterialTheme.typography.headlineMedium.toSpanStyleColored()){
                    append(" → ")
                }
                withStyle(style = MaterialTheme.typography.bodyLarge.toSpanStyleColored(color = MaterialTheme.colorScheme.primary)){
                    append(game.target)
                }
            }
        )
        Row(

        ) {
            Text(
                color = MaterialTheme.colorScheme.inverseSurface,
                text = buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyleColored(color = MaterialTheme.colorScheme.tertiary)) {
                        append("Шагов: ")
                    }
                    withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyleColored()) {
                        append(game.stepCounter.toString())
                    }
                },
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            Text(
                color = MaterialTheme.colorScheme.inverseSurface,
                text = buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyleColored(color = MaterialTheme.colorScheme.tertiary)) {
                        append("Время: ")
                    }
                    withStyle(style = MaterialTheme.typography.bodySmall.toSpanStyleColored()) {
                        append(game.time.toString())
                    }
                },
                modifier = Modifier.padding(horizontal = 5.dp)
            )
        }
    }
}

@Composable
fun ReturnActionHeader(onReturnClick: ()->Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(10.dp),
        contentAlignment = Alignment.CenterStart
    ){
        IconButton(
            onClick = onReturnClick
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_back_24px),
                contentDescription = "Return arrow",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(40.dp)
            )
        }

    }
}

@Preview
@Composable
fun GameListPreview(){
    AppTheme{
        GamesList(rememberNavController())
    }
}


object TempListOfGames{
    val list = listOf(
        Game(
            id = 1,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
        Game(
            id = 2,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
        Game(
            id = 3,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
        Game(
            id = 4,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
        Game(
            id = 5,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
        Game(
            id = 6,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
        Game(
            id = 7,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
    )
}