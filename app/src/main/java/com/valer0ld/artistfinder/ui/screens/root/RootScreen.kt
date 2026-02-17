package com.valer0ld.artistfinder.ui.screens.root

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.valer0ld.artistfinder.navigation.AuthNavGraph
import com.valer0ld.artistfinder.ui.screens.auth.AuthViewModel
import com.valer0ld.artistfinder.ui.screens.main.MainScreen
import com.valer0ld.artistfinder.ui.screens.root.components.ConfigureSystemBars
import com.valer0ld.artistfinder.ui.screens.splash.SplashScreen

@Composable
fun RootScreen(){
    ConfigureSystemBars()

    val authViewModel: AuthViewModel = hiltViewModel()

    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    val authNavController = rememberNavController()

    var showSplash by remember { mutableStateOf(true) }

    if (isLoggedIn == null || showSplash){
        SplashScreen {showSplash = false}
    }
    else{
        Crossfade(
            targetState = isLoggedIn == true,
            animationSpec = tween(600)
        ) { loggedIn ->
            if(loggedIn) MainScreen()
            else AuthNavGraph(authViewModel = authViewModel, navController = authNavController)
        }
    }

}