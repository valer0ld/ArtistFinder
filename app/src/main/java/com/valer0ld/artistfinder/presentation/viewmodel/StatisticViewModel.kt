package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.model.UserStatistic
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StatisticUIState(
    val userStats: UserStatistic = UserStatistic(
    gameStarts = 0,
    gameEnds = 0,
    totalTime = 0,
    totalSteps = 0,
    bestRating = 0
    ),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class StatisticViewModel @Inject constructor(
    private val currentUserRepository: CurrentUserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StatisticUIState())
    val uiState: StateFlow<StatisticUIState> = _uiState.asStateFlow()

    init {
        loadStats()
    }

    fun loadStats(){
        viewModelScope.launch {
            _uiState.update { it.copy (isLoading = true, error = null)}
            try{
                currentUserRepository.observeUser().collect { user ->
                    if (user == null) return@collect

                    _uiState.update {
                        it.copy(
                            userStats = UserStatistic(
                                gameStarts = user.gameStarts,
                                gameEnds = user.gameEnds,
                                totalTime = user.totalTime,
                                totalSteps = user.totalSteps,
                                bestRating = user.bestRating
                            ),
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