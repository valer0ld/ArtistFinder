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


data class RegisterUIState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val currentUserRepository: CurrentUserRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUIState())
    val uiState: StateFlow<RegisterUIState> = _uiState.asStateFlow()

    fun register() {
        viewModelScope.launch {
            if (_uiState.value.password != _uiState.value.repeatPassword) {
                _uiState.update { it.copy(error = "Пароли не совпадают") }
                return@launch
            }

            _uiState.update { it.copy(isLoading = true, error = null) }

            runCatching {
                val (token, userID) = authRepository.createToken(
                    _uiState.value.email,
                    _uiState.value.password
                )
                authRepository.saveUserToken(token, userID)
                currentUserRepository.upsertUser(
                    CurrentUser(
                        id = userID,
                        userName = _uiState.value.userName,
                        email = _uiState.value.email,
                        avatarUrl = ""
                    )
                )
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
            }.onFailure { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun onEmailChange(value: String){
        _uiState.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String){
        _uiState.update { it.copy(password = value) }
    }

    fun onUserNameChange(value: String){
        _uiState.update { it.copy(userName = value) }
    }

    fun onRepeatPasswordChange(value: String){
        _uiState.update { it.copy(repeatPassword = value) }
    }
}