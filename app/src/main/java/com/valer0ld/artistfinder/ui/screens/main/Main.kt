package com.valer0ld.artistfinder.ui.screens.main

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.valer0ld.artistfinder.ui.components.ScaffoldBottomBar
import com.valer0ld.artistfinder.ui.components.ScaffoldTopBar
import com.valer0ld.artistfinder.ui.navigation.NavGraph
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun Main(navController: NavHostController = rememberNavController()) {
    ConfigureSystemBars()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val currentStep = remember { mutableIntStateOf(0) }
    val maxReturns = 3
    val currentReturns = remember { mutableIntStateOf(maxReturns) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ScaffoldTopBar(navController, currentDestination?.route) },
        bottomBar = {
            ScaffoldBottomBar(
                navController,
                currentDestination?.route,
                currentStep.intValue,
                currentReturns.intValue
            ){
                currentStep.intValue--
                currentReturns.intValue--
            }
        },
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets.navigationBars
    ){ innerPadding ->
        NavGraph(
            navController,
            modifier = Modifier,
            innerPadding = innerPadding,
            currentStep.intValue
        ){
            currentStep.intValue++
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