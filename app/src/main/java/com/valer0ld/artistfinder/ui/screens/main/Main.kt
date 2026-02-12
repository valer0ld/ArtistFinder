package com.valer0ld.artistfinder.ui.screens.main

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.valer0ld.artistfinder.MainActivity
import com.valer0ld.artistfinder.ui.components.ScaffoldBottomBar
import com.valer0ld.artistfinder.ui.components.ScaffoldTopBar
import com.valer0ld.artistfinder.ui.navigation.HomeNavGraph
import com.valer0ld.artistfinder.ui.navigation.NavBarItems
import com.valer0ld.artistfinder.ui.navigation.NavRoutes
import com.valer0ld.artistfinder.ui.navigation.ProfileNavGraph
import com.valer0ld.artistfinder.ui.screens.rating.RatingScreen
import com.valer0ld.artistfinder.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun Main() {
    ConfigureSystemBars()

    val context = LocalContext.current
    //var backPressedTime by remember { mutableLongStateOf(0L) }

    val pagerState = rememberPagerState(pageCount = { NavBarItems.barItems.size })
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

    val currentStep = remember { mutableIntStateOf(0) }
    val maxReturns = 3
    val currentReturns = remember { mutableIntStateOf(maxReturns) }

    BackHandler {

        val nc = currentNavController
        val backStackSize = nc?.visibleEntries?.value?.size ?: 0

        if ((nc != null && backStackSize > 1)) {
            nc.popBackStack()
        }
        else{
            if (currentPage > 0) {
                coroutineScope.launch { // нужно исправить ошибку, при которой назад выполняется с прошлой страницы
                    pagerState.scrollToPage(0)
                }
            }
            else {
                /*val currentTime = System.currentTimeMillis()
                if (currentTime - backPressedTime > 2000) {
                    Toast.makeText(context, "Нажмите ещё раз для выхода", Toast.LENGTH_SHORT).show()
                    backPressedTime = currentTime
                } else {*/
                    (context as? MainActivity)?.finish()
                //}
            }
        }

    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ScaffoldTopBar(
            currentDestination = currentDestination,
            onBack = {
                currentNavController?.navigate(NavRoutes.ProfileMain.route) {
                    popUpTo(NavRoutes.ProfileMain.route) { inclusive = false }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) },
        bottomBar = {
            ScaffoldBottomBar(
                currentDestination = currentDestination,
                selectedIndex = currentPage,
                currentStep = currentStep.intValue,
                currentReturns = currentReturns.intValue,
                onGameBottomBarClick = {
                    currentStep.intValue--
                    currentReturns.intValue--
                },
                onIconClick = { index ->
                    coroutineScope.launch {
                        if (currentPage == 1 && index == 1){
                            if (currentDestination != "profile/main") {
                                currentNavController?.navigate(NavRoutes.ProfileMain.route) {
                                    popUpTo(NavRoutes.ProfileMain.route) { inclusive = false }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                        else pagerState.animateScrollToPage(index)
                    }
                },
                onHomeClick = {
                    currentNavController?.popBackStack("home", inclusive = false)
                }
            )
        },
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets.navigationBars
    ){ innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            beyondViewportPageCount = 1,
            userScrollEnabled = when (pagerState.currentPage){
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
                        currentStep = currentStep.intValue,
                        incrementStep = {
                            currentStep.intValue++
                        }
                    )
                }
                1 -> {
                    ProfileNavGraph(
                        navController = profileNavController,
                        innerPadding = innerPadding,
                        onNavigationToGame = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(0)
                                homeNavController.navigate(NavRoutes.Game.route)
                            }
                        }
                    )
                }
                2 -> RatingScreen()
                else -> Box(Modifier.fillMaxSize(), Alignment.Center) { Text("404") }
            }

        }
    }
}

@Composable
fun ConfigureSystemBars() {
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    val colorScheme = MaterialTheme.colorScheme

    LaunchedEffect(colorScheme.background) {
        activity?.window?.let { window ->
            val isDarkBackground = colorScheme.background.luminance() < 0.5f

            WindowCompat.setDecorFitsSystemWindows(window, false)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // Android 11+
                window.isNavigationBarContrastEnforced = false

                window.navigationBarColor = Color.Transparent.toArgb()
                window.statusBarColor = Color.Transparent.toArgb()

                WindowCompat.getInsetsController(window, window.decorView).apply {
                    isAppearanceLightNavigationBars = !isDarkBackground
                    isAppearanceLightStatusBars = !isDarkBackground
                }
            } else {
                // API 21–29
                @Suppress("DEPRECATION")
                window.navigationBarColor = Color.Transparent.toArgb()
                @Suppress("DEPRECATION")
                window.statusBarColor = Color.Transparent.toArgb()

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    WindowCompat.getInsetsController(window, window.decorView).apply {
                        isAppearanceLightNavigationBars = !isDarkBackground
                        isAppearanceLightStatusBars = !isDarkBackground
                    }
                }
            }
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