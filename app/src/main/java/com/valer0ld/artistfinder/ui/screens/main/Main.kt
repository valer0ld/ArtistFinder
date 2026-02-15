package com.valer0ld.artistfinder.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.valer0ld.artistfinder.ui.components.ScaffoldBottomBar
import com.valer0ld.artistfinder.ui.navigation.HomeNavGraph
import com.valer0ld.artistfinder.ui.navigation.NavBarItems
import com.valer0ld.artistfinder.ui.navigation.NavRoutes
import com.valer0ld.artistfinder.ui.navigation.NavigationGuard
import com.valer0ld.artistfinder.ui.navigation.ProfileNavGraph
import com.valer0ld.artistfinder.ui.screens.game.GameViewModel
import com.valer0ld.artistfinder.ui.screens.main.components.ConfigureSystemBars
import com.valer0ld.artistfinder.ui.screens.main.components.CustomBackHandler
import com.valer0ld.artistfinder.ui.screens.rating.RatingScreen
import com.valer0ld.artistfinder.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun Main() {
    ConfigureSystemBars()

    val pagerState = rememberPagerState(pageCount = { NavBarItems.barItems.size }, initialPage = 0)
    val coroutineScope = rememberCoroutineScope()

    val homeNavController = rememberNavController()
    val profileNavController = rememberNavController()

    val navControllers = remember {
        mapOf(
            0 to homeNavController,
            1 to profileNavController,
        )
    }

    val currentPage by remember(pagerState)  { derivedStateOf{ pagerState.currentPage } }
    val currentNavController by remember(currentPage) { derivedStateOf { navControllers[currentPage] }}

    val currentBackStackEntry = currentNavController?.currentBackStackEntryAsState()?.value
    val currentDestination = currentBackStackEntry?.destination?.route

    val gameViewModel: GameViewModel = viewModel()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            ScaffoldBottomBar(
                currentDestination = currentDestination,
                selectedIndex = currentPage,
                gameViewModel = gameViewModel,
                onIconClick = { index ->
                    coroutineScope.launch {
                        if (currentPage == 1 && index == 1){
                            if (currentDestination != "profile/main") {
                                profileNavController.popBackStack()
                            }
                        }
                        else pagerState.scrollToPage(index)
                    }
                },
                onHomeClick = {
                    homeNavController.popBackStack("home", inclusive = false)
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.navigationBars
    ){ innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            beyondViewportPageCount = 0,
            userScrollEnabled = when (currentPage){
                0 -> currentDestination == NavRoutes.Home.route
                1 -> true
                2 -> true
                else -> false
            },
        ) { page ->
            when (page){
                0 -> {
                    HomeNavGraph(
                        navController = homeNavController,
                        innerPadding = innerPadding,
                        gameViewModel = gameViewModel
                    )
                }
                1 -> {
                    ProfileNavGraph(
                        navController = profileNavController,
                        innerPadding = innerPadding,
                        onNavigationToHomeAndGame = {
                            coroutineScope.launch {
                                NavigationGuard.safeNavigate{
                                    profileNavController.popBackStack()
                                    pagerState.scrollToPage(0)
                                    homeNavController.navigate(NavRoutes.Game.route){
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }

                            }
                        },
                    )
                }
                2 -> RatingScreen(innerPadding)
                else -> Box(Modifier.fillMaxSize(), Alignment.Center) { Text("404") }
            }

            CustomBackHandler(pagerState, navControllers)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun MainPreview(){
    AppTheme(dynamicColor = false) {
        Main()
    }
}