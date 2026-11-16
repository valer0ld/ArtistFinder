package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.model.Artist
import com.valer0ld.artistfinder.domain.model.Game
import com.valer0ld.artistfinder.domain.model.TempListOfGames
import com.valer0ld.artistfinder.domain.repository.CurrentGameRepository
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
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
    private val currentUserRepository: CurrentUserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameListUIState())
    val uiState: StateFlow<GameListUIState> = _uiState.asStateFlow()

    init {
        loadList()
    }

    fun onGameStart(start: Artist, target: Artist){
        viewModelScope.launch {
            currentGameRepository.startGame(start, target, false)
            currentUserRepository.startGame()
        }
    }

    fun loadList(){
        viewModelScope.launch {
            _uiState.update { it.copy (isLoading = true, error = null)}
            try{
                val list = TempListOfGames.list
                _uiState.update {
                    it.copy(
                        gameList = list,
                        isLoading = false
                    )
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