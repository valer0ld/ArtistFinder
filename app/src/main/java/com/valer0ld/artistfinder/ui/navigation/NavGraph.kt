package com.valer0ld.artistfinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.valer0ld.artistfinder.ui.screens.Home
import com.valer0ld.artistfinder.ui.screens.Profile
import com.valer0ld.artistfinder.ui.screens.Rating
import com.valer0ld.artistfinder.ui.screens.Game

sealed class NavRoutes(val route: String){
    object Home: NavRoutes("home")
    object Profile: NavRoutes("profile")
    object Rating: NavRoutes("rating")
    object Game: NavRoutes("game")
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier){
    NavHost(navController = navController, startDestination = NavRoutes.Home.route, modifier = modifier){
        composable(NavRoutes.Home.route) { Home() }
        composable(NavRoutes.Profile.route) { Profile() }
        composable(NavRoutes.Rating.route) { Rating() }
        composable(NavRoutes.Game.route) { Game() }
    }
}