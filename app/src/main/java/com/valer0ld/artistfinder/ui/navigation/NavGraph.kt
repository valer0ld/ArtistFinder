package com.valer0ld.artistfinder.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.valer0ld.artistfinder.ui.screens.Home
import com.valer0ld.artistfinder.ui.screens.SettingsScreen
import com.valer0ld.artistfinder.ui.screens.Rating
import com.valer0ld.artistfinder.ui.screens.Game
import com.valer0ld.artistfinder.ui.screens.GamesList
import com.valer0ld.artistfinder.ui.screens.ProfileMain
import com.valer0ld.artistfinder.ui.screens.WinGame

sealed class NavRoutes(val route: String){
    object Home: NavRoutes("home")
    object Profile: NavRoutes("profile")
    object ProfileMain: NavRoutes("profile/main")
    object GameList: NavRoutes("profile/games")
    object Settings: NavRoutes("profile/settings")
    object Rating: NavRoutes("rating")
    object Game: NavRoutes("game")
    object WinGame: NavRoutes("win")
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier, currentStep: Int, incrementStep: ()->Unit){
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
        modifier = modifier,

        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(durationMillis = 90)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it / 4 },
                animationSpec = tween(durationMillis = 90)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it / 4 },
                animationSpec = tween(durationMillis = 90)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(durationMillis = 90)
            )
        }){
        composable(NavRoutes.Home.route) { Home(navController) }
        composable(NavRoutes.Rating.route) { Rating() }
        composable(NavRoutes.Game.route) { Game(navController, currentStep, incrementStep) }
        composable(NavRoutes.WinGame.route) { WinGame() }

        navigation(
            startDestination = NavRoutes.ProfileMain.route,
            route = NavRoutes.Profile.route
        ){
            composable(NavRoutes.ProfileMain.route) { ProfileMain(
                onGamesClick = { navController.navigate(NavRoutes.GameList.route) },
                onSettingsClick = { navController.navigate(NavRoutes.Settings.route) }
            ) }
            composable(NavRoutes.GameList.route) { GamesList() }
            composable(NavRoutes.Settings.route) { SettingsScreen() }
        }
    }

}