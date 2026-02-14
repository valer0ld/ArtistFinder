package com.valer0ld.artistfinder.ui.screens.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.ui.components.BouncingIconButton
import com.valer0ld.artistfinder.ui.theme.AppTheme


@Composable
fun GameBottomBar(
    currentStep: Int,
    currentReturns: Int,
    modifier: Modifier = Modifier,
    returnClick: ()->Unit,
    onHomeClick: ()->Unit
){

    Surface(
        shape = RoundedCornerShape(30.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .then(modifier)
            .fillMaxWidth()
            .height(70.dp),
        shadowElevation = 4.dp
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            BouncingIconButton(
                icon = R.drawable.close_24px,
                label = "close",
                color = MaterialTheme.colorScheme.error,
                size = 60.dp,
                modifier = Modifier.padding(horizontal = 30.dp),
                onIconClick = onHomeClick
            )
            Row(modifier = Modifier.padding(horizontal = 30.dp)) {
                val enabled = (currentReturns > 0 && currentStep > 0)

                Icon(
                    painter = painterResource(CounterIconItems.IconItems[currentReturns].image),
                    contentDescription = CounterIconItems.IconItems[currentReturns].title,
                    tint = if (enabled) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.padding(vertical = 5.dp).size(30.dp)
                )

                BouncingIconButton(
                    icon = R.drawable.replay_24px,
                    label = "return",
                    color = if (enabled) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.outlineVariant,
                    size = 60.dp,
                    enabled = enabled,
                    onIconClick = returnClick
                )
            }
        }
    }
}

@Preview
@Composable
fun GameBottomBarPreview(){
    AppTheme {
        GameBottomBar(currentStep = 3, 2, Modifier, {}, {})
    }
}

data class GameBarItem(
    val title: String,
    val image: Int
)

object CounterIconItems{
    val IconItems = listOf(
        GameBarItem(
            title = "counter 0",
            image = R.drawable.counter_0_24px
        ),
        GameBarItem(
            title = "counter 1",
            image = R.drawable.counter_1_24px
        ),
        GameBarItem(
            title = "counter 2",
            image = R.drawable.counter_2_24px
        ),
        GameBarItem(
            title = "counter 3",
            image = R.drawable.counter_3_24px
        )
    )
}