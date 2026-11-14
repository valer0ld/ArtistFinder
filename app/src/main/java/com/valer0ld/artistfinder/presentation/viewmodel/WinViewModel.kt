package com.valer0ld.artistfinder.presentation.viewmodel

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.model.Artist
import com.valer0ld.artistfinder.domain.repository.CurrentGameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WinUIState(
    val steps: List<Artist> = emptyList(),
    val totalStep: Int = 0,
    val duration: Long = 0
)


@HiltViewModel
class WinViewModel @Inject constructor(
    private val currentGameRepository: CurrentGameRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WinUIState())
    val uiState: StateFlow<WinUIState> = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            currentGameRepository.game.collect { game ->
                if (game == null) return@collect

                _uiState.update {
                    it.copy(
                        steps = game.steps,
                        totalStep = game.currentStep,
                        duration = game.duration
                    )
                }
            }
        }
    }

    fun getStartArtist() = _uiState.value.steps.first()

    fun getTargetArtist() = _uiState.value.steps.last()

}