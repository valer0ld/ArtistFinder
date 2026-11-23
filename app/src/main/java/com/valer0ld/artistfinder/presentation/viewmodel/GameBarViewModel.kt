package com.valer0ld.artistfinder.presentation.viewmodel

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.manager.GameSessionManager
import com.valer0ld.artistfinder.domain.repository.CurrentGameRepository
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import com.valer0ld.artistfinder.domain.repository.GameListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject


data class GameBarUIState(
    val currentStep: Int = 0,
    val currentReturns: Int = 0,
    val startTime: Long = 0
)

@HiltViewModel
class GameBarViewModel @Inject constructor(
    private val currentGameRepository: CurrentGameRepository,
    private val currentUserRepository: CurrentUserRepository,
    private val gameListRepository: GameListRepository,
    private val gameSessionManager: GameSessionManager
): ViewModel() {

    private val _uiState = MutableStateFlow(GameBarUIState())
    val uiState: StateFlow<GameBarUIState> = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            currentGameRepository.game.collect { game ->
                if (game == null) return@collect

                _uiState.update {
                    it.copy(
                        currentStep = game.currentStep,
                        currentReturns = game.currentReturns,
                        startTime = game.startTime
                    )
                }
            }
        }
    }

    suspend fun onCancelGame() {

        gameSessionManager.mutex.lock()
        try{
            if (!gameSessionManager.isFinalized){
                val game = currentGameRepository.onCancelGame(SystemClock.elapsedRealtime() - _uiState.value.startTime)
                currentUserRepository.addGameStats(game.isDaily, game.duration, game.currentStep, false)
                gameListRepository.upsert(game)

                gameSessionManager.markFinalized()
            }
        } finally {
            gameSessionManager.mutex.unlock()
        }
    }

    fun onReturnClick(){
        viewModelScope.launch {
            currentGameRepository.useReturn()
        }
    }

    fun onUpdateState(){
        viewModelScope.launch {
            currentGameRepository.clear()
        }
    }
}