package com.valer0ld.artistfinder.ui.screens.main.components

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import kotlinx.coroutines.launch


@Composable
fun CustomBackHandler(pagerState: PagerState, navControllers: Map<Int, NavController>){
    var backPressedTime by remember { mutableLongStateOf(0L) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val currentPage = pagerState.currentPage
    val currentNavController = navControllers[currentPage]

    BackHandler(enabled = true){
        if (currentNavController != null && currentNavController.previousBackStackEntry != null) {
            currentNavController.popBackStack()
        }
        else{
            if (currentPage > 0) {
                coroutineScope.launch {
                    pagerState.scrollToPage(0)
                }
            } else {
                val currentTime = System.currentTimeMillis()
                if (currentTime - backPressedTime > 2000) {
                    Toast.makeText(context, "Нажмите ещё раз для выхода", Toast.LENGTH_SHORT).show()
                    backPressedTime = currentTime
                } else {
                    (context as? Activity)?.finish()
                }
            }
        }
    }
}