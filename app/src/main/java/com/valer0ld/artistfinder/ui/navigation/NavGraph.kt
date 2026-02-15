package com.valer0ld.artistfinder.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.valer0ld.artistfinder.ui.screens.home.Home
import com.valer0ld.artistfinder.ui.screens.settings.SettingsScreen
import com.valer0ld.artistfinder.ui.screens.game.GameScreen
import com.valer0ld.artistfinder.ui.screens.game.GameViewModel
import com.valer0ld.artistfinder.ui.screens.game_list.GamesList
import com.valer0ld.artistfinder.ui.screens.profile_main.ProfileMain
import com.valer0ld.artistfinder.ui.screens.user_stats.StatisticScreen
import com.valer0ld.artistfinder.ui.screens.win_game.WinGameScreen
import kotlinx.coroutines.launch

sealed class NavRoutes(val route: String){
    object Home: NavRoutes("home")
    object Game: NavRoutes("game")
    object WinGame: NavRoutes("win")
    object ProfileMain: NavRoutes("profile/main")
    object GameList: NavRoutes("profile/games")
    object Settings: NavRoutes("profile/settings")
    object Statistic: NavRoutes("profile/statistic")
}

@Composable
fun NavGraph(navController: NavHostController, startDestination: String, innerPadding: PaddingValues, builder: NavGraphBuilder.()->Unit){

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier
            .padding(
                start = innerPadding.calculateLeftPadding(LayoutDirection.Ltr),
                end = innerPadding.calculateRightPadding(LayoutDirection.Ltr),
                top = innerPadding.calculateTopPadding()
            ),

        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                //animationSpec = tween(durationMillis = 90)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it / 4 },
                //animationSpec = tween(durationMillis = 90)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it / 4 },
                //animationSpec = tween(durationMillis = 90)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                //animationSpec = tween(durationMillis = 90)
            )
        },
        builder = builder
    )
}

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    gameViewModel: GameViewModel
){
    val coroutineScope = rememberCoroutineScope()

    NavGraph(
        navController = navController,
        innerPadding = innerPadding,
        startDestination = NavRoutes.Home.route,
    ){
        composable(NavRoutes.Home.route) { Home(
            innerPadding = innerPadding,
            onGameClick = {
                coroutineScope.launch {
                    NavigationGuard.safeNavigate {
                        navController.navigate("game"){
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        ) }
        composable(NavRoutes.Game.route) { GameScreen(
            innerPadding = innerPadding,
            viewModel = gameViewModel,
            onWinGame = {
                coroutineScope.launch {
                    NavigationGuard.safeNavigate {
                        navController.navigate("win"){
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        ) }
        composable(NavRoutes.WinGame.route) { WinGameScreen() }
    }
}

@Composable
fun ProfileNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onNavigationToHomeAndGame: ()->Unit
){
    val coroutineScope = rememberCoroutineScope ()

    NavGraph(
        navController = navController,
        innerPadding = innerPadding,
        startDestination = NavRoutes.ProfileMain.route,
    ){
        composable(NavRoutes.ProfileMain.route) { ProfileMain(
            innerPadding,
            onGamesClick = {
                coroutineScope.launch {
                    NavigationGuard.safeNavigate {
                        navController.navigate(NavRoutes.GameList.route){
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                } },
            onSettingsClick = {
                coroutineScope.launch {
                    NavigationGuard.safeNavigate {
                        navController.navigate(NavRoutes.Settings.route){
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                } },
            onStatsClick = {
                coroutineScope.launch {
                    NavigationGuard.safeNavigate {
                        navController.navigate(NavRoutes.Statistic.route){
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                } }
        ) }
        composable(NavRoutes.GameList.route) { GamesList(
            onGoToGame = { onNavigationToHomeAndGame() },
            onBackArrow = { navController.popBackStack() }
        ) }
        composable(NavRoutes.Settings.route) { SettingsScreen(
            onBackArrow = { navController.popBackStack() }
        ) }
        composable(NavRoutes.Statistic.route) { StatisticScreen(
                innerPadding = innerPadding,
                onBackArrow = { navController.popBackStack() }
            )
        }
    }
}