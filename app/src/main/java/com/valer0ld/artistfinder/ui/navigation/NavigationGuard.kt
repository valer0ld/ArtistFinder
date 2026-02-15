package com.valer0ld.artistfinder.ui.navigation

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