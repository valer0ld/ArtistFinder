package com.valer0ld.artistfinder.ui.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.valer0ld.artistfinder.ui.components.BouncingIconButton
import com.valer0ld.artistfinder.ui.theme.AppTheme


@Composable
fun GameBottomBar(navController: NavController, currentStep: Int, currentReturns: Int, modifier: Modifier = Modifier, returnClick: ()->Unit){

    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 10.dp)
            .then(modifier)
            .fillMaxWidth()
            .height(70.dp)
            .shadow(2.dp, shape = RoundedCornerShape(30.dp))
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        BouncingIconButton(
            icon = R.drawable.close_24px,
            label = "close",
            color = MaterialTheme.colorScheme.error,
            size = 60.dp,
            modifier = Modifier.padding(horizontal = 30.dp),
            onIconClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                }
            }
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

@Preview
@Composable
fun GameBottomBarPrevew(){
    AppTheme {
        GameBottomBar(navController = rememberNavController(), currentStep = 3, 2, returnClick = {  })
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