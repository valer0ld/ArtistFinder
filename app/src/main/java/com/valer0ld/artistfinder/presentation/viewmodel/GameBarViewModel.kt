package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.repository.CurrentGameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class GameBarUIState(
    val currentStep: Int = 0,
    val currentReturns: Int = 0
)

@HiltViewModel
class GameBarViewModel @Inject constructor(
    private val currentGameRepository: CurrentGameRepository
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
                        currentReturns = game.currentReturns
                    )
                }
            }
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