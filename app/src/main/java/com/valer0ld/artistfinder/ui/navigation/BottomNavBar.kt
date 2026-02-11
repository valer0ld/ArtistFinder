package com.valer0ld.artistfinder.ui.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.valer0ld.artistfinder.ui.components.BouncingIconButton
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier = Modifier){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination

    val isInProfileGraph = currentRoute
        ?.hierarchy
        ?.any { it.route == NavRoutes.Profile.route }
        ?: false
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
        Row {
            NavBarItems.BarItems.forEach { item ->

                val isSelected = when(item.route){
                    "profile" -> isInProfileGraph
                    else -> currentRoute?.route == item.route
                }

                BottomBarItem(
                    item = item,
                    selected = isSelected,
                    onIconClick = {
                        if (item.route == "profile" && isInProfileGraph) {
                            if (currentRoute?.route != "profile/main") {
                                navController.navigate("profile/main") {
                                    popUpTo(item.route) {
                                        inclusive = false
                                    }
                                    launchSingleTop = true
                                    restoreState = false
                                }
                            }
                        } else {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun BottomBarItem(
    item: BarItem,
    selected: Boolean,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier
){
    BouncingIconButton(
        icon = item.image,
        label =item.title,
        color = if (selected) {
            MaterialTheme.colorScheme.tertiaryContainer
        } else {
            MaterialTheme.colorScheme.secondaryContainer
        },
        size = 60.dp,
        onIconClick = onIconClick,
        modifier = modifier
    )
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