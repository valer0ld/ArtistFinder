package com.valer0ld.artistfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.valer0ld.artistfinder.ui.screens.Main
import com.valer0ld.artistfinder.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme(dynamicColor = false) {
                Main()
            }
        }
    }
}

