package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.model.RatingPlayer
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import com.valer0ld.artistfinder.domain.repository.PublicUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
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
class RatingViewModel @Inject constructor(
    private val publicUserRepository: PublicUserRepository,
    private val currentUserRepository: CurrentUserRepository// временно, пока нет бека, чтобы хотя бы один пользователь был в таблице

) : ViewModel() {
    private val _uiState = MutableStateFlow(RatingUIState())
    val uiState: StateFlow<RatingUIState> = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            currentUserRepository.observeUser().collect { user ->
                if (user == null) return@collect

                publicUserRepository.refreshUsers(listOf(
                    RatingPlayer(
                        id = user.id,
                        username = user.userName,
                        pictureUrl = user.avatarUrl,
                        dailyGames = user.dailyGameEnds,
                        dailySteps = user.dailySteps,
                        dailyTime = user.dailyTime,
                        sumScore = user.dailySteps * 10 + user.dailyGameEnds + (user.dailyTime / 1000).toInt(),
                        place = user.currentRating
                    )
                ))
            }
        }
        loadRating()
    }

    fun loadRating(){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try{
                combine(
                    publicUserRepository.observeUser(),
                    publicUserRepository.observeUsers()
                ) { player, players ->
                    RatingUIState(
                        players = players ?: emptyList(),
                        currentPlayer = player ?: RatingPlayer(-1, "ошибка"),
                        isLoading = false
                    )
                }.collect{ state ->
                    _uiState.value = state
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