package com.valer0ld.artistfinder.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.model.Game
import com.valer0ld.artistfinder.domain.repository.CurrentGameRepository
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import com.valer0ld.artistfinder.domain.repository.GameListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GameListUIState(
    val gameList: List<Game> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val currentGameRepository: CurrentGameRepository,
    private val currentUserRepository: CurrentUserRepository,
    private val gameListRepository: GameListRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameListUIState())
    val uiState: StateFlow<GameListUIState> = _uiState.asStateFlow()

    init {
        loadList()
    }

    fun onGameStart(id: Long, onSuccess: ()->Unit){
        viewModelScope.launch {
            try {
                val game = gameListRepository.getGame(id)

                if (game != null){
                    currentGameRepository.startGame(game.start, game.target, false)
                    currentUserRepository.startGame(false)
                    onSuccess()
                }
            } catch (e: Exception) {
            Log.e("MY_LOG", "Ошибка при запуске игры", e)
            }
        }
    }

    fun loadList(){
        viewModelScope.launch {
            _uiState.update { it.copy (isLoading = true, error = null)}
            try{
                gameListRepository.observeGames().collect { games ->
                    if (games == null) return@collect

                    _uiState.update {
                        it.copy(
                            gameList = games.asReversed(),
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Неизвестная ошибка"
                    )
                }
            }
        }
    }
}