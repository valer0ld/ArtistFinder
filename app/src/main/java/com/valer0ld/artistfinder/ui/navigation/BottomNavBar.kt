package com.valer0ld.artistfinder.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier = Modifier){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 10.dp)
            .then(modifier)
            .fillMaxWidth()
            .height(70.dp)
            .shadow(2.dp, shape = RoundedCornerShape(30.dp))
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        NavBarItems.BarItems.forEach { item ->
            BottomBarItem(
                item = item,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun BottomBarItem(
    item: BarItem,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .fillMaxHeight()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(item.image),
            contentDescription = item.title,
            tint = if (selected) {
                MaterialTheme.colorScheme.tertiaryContainer
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            },
            modifier = Modifier.size(50.dp)
        )
    }
}

data class BarItem(
    val title: String,
    val image: Int,
    val route: String
)


object NavBarItems{
    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = R.drawable.stadia_controller_24px,
            route = "home"
        ),
        BarItem(
            title = "Profile",
            image = R.drawable.profile_icon,
            route = "profile"
        ),
        BarItem(
            title = "Rating",
            image = R.drawable.social_leaderboard_24px,
            route = "rating"
        )
    )
}

@Preview
@Composable
fun BottomNavBarPreview(){
    AppTheme {
        BottomNavigationBar(navController = rememberNavController())
    }
}