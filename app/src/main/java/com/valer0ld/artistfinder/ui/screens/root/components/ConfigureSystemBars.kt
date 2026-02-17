package com.valer0ld.artistfinder.ui.screens.root.components

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat


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
