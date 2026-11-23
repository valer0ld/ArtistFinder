package com.valer0ld.artistfinder.domain.manager

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.sync.Mutex
import javax.inject.Inject


@ActivityRetainedScoped
class GameSessionManager @Inject constructor(){

    val mutex = Mutex()

    var isFinalized: Boolean = false
        private set

    fun markFinalized(){
        isFinalized = true
    }

    fun reset(){
        isFinalized = false
    }
}