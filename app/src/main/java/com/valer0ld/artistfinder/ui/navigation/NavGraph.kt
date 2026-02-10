package com.valer0ld.artistfinder.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.valer0ld.artistfinder.ui.screens.home.Home
import com.valer0ld.artistfinder.ui.screens.settings.SettingsScreen
import com.valer0ld.artistfinder.ui.screens.rating.RatingScreen
import com.valer0ld.artistfinder.ui.screens.game.GameScreen
import com.valer0ld.artistfinder.ui.screens.game_list.GamesList
import com.valer0ld.artistfinder.ui.screens.profile_main.ProfileMain
import com.valer0ld.artistfinder.ui.screens.win_game.WinGameScreen

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
        composable(NavRoutes.Rating.route) { RatingScreen() }
        composable(NavRoutes.Game.route) { GameScreen(navController, currentStep, incrementStep) }
        composable(NavRoutes.WinGame.route) { WinGameScreen() }

        navigation(
            startDestination = NavRoutes.ProfileMain.route,
            route = NavRoutes.Profile.route
        ){
            composable(NavRoutes.ProfileMain.route) { ProfileMain(
                onGamesClick = { navController.navigate(NavRoutes.GameList.route) },
                onSettingsClick = { navController.navigate(NavRoutes.Settings.route) }
            ) }
            composable(NavRoutes.GameList.route) { GamesList(navController) }
            composable(NavRoutes.Settings.route) { SettingsScreen(navController) }
        }
    }

}