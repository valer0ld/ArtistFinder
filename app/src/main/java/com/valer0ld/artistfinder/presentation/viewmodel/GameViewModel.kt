package com.valer0ld.artistfinder.presentation.viewmodel

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.manager.GameSessionManager
import com.valer0ld.artistfinder.domain.model.Artist
import com.valer0ld.artistfinder.domain.repository.CurrentGameRepository
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import com.valer0ld.artistfinder.domain.repository.GameListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds


data class GameUIState(
    val targetArtist: Artist = Artist("", 0),
    val currentArtist: Artist = Artist("", 0),
    val similarArtists: List<Artist> = emptyList(),
    val currentStep: Int = 0,
    val currentReturns: Int = 0,
    val processingButtonId: Int? = null,
    val startTime: Long = 0
)

@HiltViewModel
class GameViewModel @Inject constructor(
    private val currentGameRepository: CurrentGameRepository,
    private val currentUserRepository: CurrentUserRepository,
    private val gameListRepository: GameListRepository,
    private val gameSessionManager: GameSessionManager
): ViewModel() {

    private val _uiState = MutableStateFlow(GameUIState())
    val uiState: StateFlow<GameUIState> = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            currentGameRepository.game.collect { game ->
                if (game == null) return@collect

                _uiState.update {
                    it.copy(
                        targetArtist = game.targetArtist,
                        currentArtist = game.currentArtist,
                        similarArtists = game.currentArtist.similarArtist ?: emptyList(),
                        currentStep = game.currentStep,
                        currentReturns = game.currentReturns,
                        startTime = game.startTime
                    )
                }
            }
        }
    }

    fun onArtistButtonClick(buttonId: Int, artist: Artist, onWinGame: ()->Unit) {

        if (_uiState.value.processingButtonId != null) return

        _uiState.update { it.copy (processingButtonId = buttonId) }

        viewModelScope.launch {
            try {
                gameSessionManager.mutex.lock()
                try {
                    if (!gameSessionManager.isFinalized){
                        currentGameRepository.selectArtist(artist)
                        if (currentGameRepository.isWin()){

                            val game = currentGameRepository.onWin(SystemClock.elapsedRealtime() - _uiState.value.startTime)
                            currentUserRepository.addGameStats(game.isDaily, game.duration, game.currentStep, true)
                            gameListRepository.upsert(game)

                            gameSessionManager.markFinalized()

                            onWinGame()
                        }
                    }
                } finally {
                    gameSessionManager.mutex.unlock()
                }

                delay(100.milliseconds)
            } finally {
                _uiState.update { it.copy (processingButtonId = null) }
            }
        }
    }

    fun onUpdateState(){
        viewModelScope.launch{
            currentGameRepository.clear()
        }
    }
}