package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.model.RatingPlayer
import com.valer0ld.artistfinder.domain.model.TemporaryPlayerList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RatingUIState(
    val players: List<RatingPlayer> = emptyList(),
    val currentPlayer: RatingPlayer = RatingPlayer(-1, "ошибка"),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class RatingViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(RatingUIState())
    val uiState: StateFlow<RatingUIState> = _uiState.asStateFlow()

    init{
        loadRating()
    }

    fun loadRating(){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try{
                val players = TemporaryPlayerList.players.sortedBy { it.place }
                val currentPlayer = TemporaryPlayerList.currentPlayer
                _uiState.update {
                    it.copy (
                        players = players,
                        currentPlayer = currentPlayer,
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