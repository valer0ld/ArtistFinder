package com.valer0ld.artistfinder.presentation.navigation

import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

object NavigationGuard {
    private var isNavigating = false

    suspend fun safeNavigate (block: suspend () -> Unit){
        if (!isNavigating){
            isNavigating = true
            try {
                block()
            } finally {
                delay(500)
                isNavigating = false
            }
        }
    }
}

suspend fun NavHostController.safePopBackStack(){
    NavigationGuard.safeNavigate {
        this.popBackStack()
    }
}