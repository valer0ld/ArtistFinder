package com.valer0ld.artistfinder.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.ui.components.FromArtistToArtist
import com.valer0ld.artistfinder.ui.components.TitleAndContentText
import com.valer0ld.artistfinder.ui.models.TextPreset
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun WinGame(){
    var expanded by remember{ mutableStateOf(false) }
    val listState = rememberLazyListState()

    LaunchedEffect(expanded) {
        if (!expanded) {
            listState.animateScrollBy(
                value = 0f,
                animationSpec = tween(
                    durationMillis = 600,
                    easing = FastOutSlowInEasing
                )
            )
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp,top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        userScrollEnabled = expanded
    ){
        item{
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Icon(
                    painter = painterResource(R.drawable.trophy_24px),
                    contentDescription = "win trophy",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(70.dp)
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
            StatisticContainer()
        }

        stickyHeader {
            Header(
                expanded = expanded,
                onToggle = {
                    expanded = !expanded
                }
            )
        }

        item {
            AnimatedStepsList(expanded)
        }
    }
}

@Composable
fun StatisticContainer(){
    Spacer(Modifier.height(80.dp))
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
    Spacer(Modifier.height(60.dp))
}

object TemporarySteps{
    val step = listOf(
        "Воскресенье",
        "Земфира",
        "ДДТ",
        "СВ",
        "Аквариум",
        "Машина времени",
        "Чайф"
    )
}

@Composable
fun StepsItem(index: Int, artist: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text = index.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiaryContainer, shape = CircleShape)
                .padding(3.dp)
                .size(20.dp),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = artist,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.tertiary,
        )
    }
}

@Preview
@Composable
fun StepsItemPreview(){
    AppTheme{
        StepsItem(1,"Воскресенье")
    }
}

@Composable
fun Header(expanded: Boolean, onToggle: ()->Unit){

    val angle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = tween(400)
    )

    Surface(
        tonalElevation = 4.dp,
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggle() }
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(R.drawable.conversion_path_24px),
                contentDescription = "route",
                tint = if (expanded)
                    MaterialTheme.colorScheme.primary
                    else
                    MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "Показать маршрут",
                color = if (expanded)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.secondaryContainer,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f).padding(start = 10.dp)
            )
            Icon(
                painter = painterResource(R.drawable.keyboard_arrow_down_24px),
                contentDescription = "expandArrow",
                tint = if (expanded)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.size(40.dp).rotate(angle)
            )
        }
    }

}

@Composable
fun AnimatedStepsList(expanded: Boolean){
    AnimatedContent(
        targetState = expanded,
        transitionSpec = {
            slideInVertically { -it } + fadeIn() togetherWith
                    slideOutVertically { -it } + fadeOut()
        },
        label = "steps"
    ) { isExpanded ->
        if (isExpanded) {
            Column (){
                TemporarySteps.step.forEachIndexed { index, item ->
                    StepsItem(index, item)
                }
            }
        } else {
            Spacer(Modifier.height(0.dp))
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