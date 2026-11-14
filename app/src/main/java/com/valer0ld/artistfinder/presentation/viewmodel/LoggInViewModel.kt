package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.model.CurrentUser
import com.valer0ld.artistfinder.domain.repository.AuthRepository
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String

data class LoggInUIState(
    val email: String = "",
    val password: String = ""
)

@HiltViewModel
class LoggInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val currentUserRepository: CurrentUserRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(LoggInUIState())
    val uiState: StateFlow<LoggInUIState> = _uiState.asStateFlow()

    fun login(){
        viewModelScope.launch {
            val (token, userID) = authRepository.createToken(_uiState.value.email, _uiState.value.password)

            authRepository.saveUserToken(token, userID)
            // по полученному id, получаю из API user сейчас это будет затычка
            val tempUser = CurrentUser(
                id = userID,
                userName = "valera",
                email = "valer@mail.ru",
                avatarUrl = "",
                gameStarts = 0,
                gameEnds = 0,
                totalTime = 0,
                totalSteps = 0,
                bestRating = 0
            )
            currentUserRepository.upsertUser(tempUser)
        }
    }

    fun onEmailChange(value: String){
        _uiState.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String){
        _uiState.update { it.copy(password = value) }
    }
}