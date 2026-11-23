package com.valer0ld.artistfinder

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valer0ld.artistfinder.domain.repository.SettingsRepository
import com.valer0ld.artistfinder.presentation.screens.root.RootScreen
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition { false }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        @Suppress("SourceLockedOrientationActivity")
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT

        setContent {
            val isDarkTheme by settingsRepository.isDarkTheme.collectAsStateWithLifecycle(initialValue = null)

            val systemTheme = isSystemInDarkTheme()

            LaunchedEffect(Unit) {
                val savedState = settingsRepository.isDarkTheme.first()

                if (savedState == null){
                    settingsRepository.setTheme(systemTheme)
                }
            }

            AppTheme(
                dynamicColor = false,
                darkTheme = isDarkTheme ?: systemTheme
            ) {
                RootScreen()
            }
        }
    }
}

