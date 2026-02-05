package com.valer0ld.artistfinder.ui.screens

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.valer0ld.artistfinder.ui.components.GameBottomBar
import com.valer0ld.artistfinder.ui.navigation.BottomNavigationBar
import com.valer0ld.artistfinder.ui.navigation.NavGraph
import com.valer0ld.artistfinder.ui.navigation.NavRoutes
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun Main(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val currentTurn = remember { mutableIntStateOf(0) }
    val maxReturns = 3
    val currentReturns = remember { mutableIntStateOf(maxReturns) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentDestination?.route in listOf(
                NavRoutes.Home.route,
                NavRoutes.Profile.route,
                NavRoutes.Rating.route
        )){
            BottomNavigationBar(navController,
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars))
            }
            if (currentDestination?.route == NavRoutes.Game.route){
                GameBottomBar(navController,
                    currentTurn.intValue,
                    currentReturns.intValue,
                    modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)) {
                currentTurn.intValue--
                currentReturns.intValue--
            }}
        },
        containerColor = MaterialTheme.colorScheme.surface
    ){ innerPadding ->
        NavGraph(
            navController,
            modifier = Modifier
                .padding(innerPadding),
            currentTurn.intValue
        ){
            currentTurn.intValue++
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