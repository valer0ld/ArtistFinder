package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.model.CurrentUser
import com.valer0ld.artistfinder.domain.repository.AuthRepository
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import com.valer0ld.artistfinder.domain.validation.error_enums.ConfirmPasswordError
import com.valer0ld.artistfinder.domain.validation.error_enums.EmailError
import com.valer0ld.artistfinder.domain.validation.error_enums.PasswordError
import com.valer0ld.artistfinder.domain.validation.validators.validateConfirmPassword
import com.valer0ld.artistfinder.domain.validation.validators.validateEmail
import com.valer0ld.artistfinder.domain.validation.validators.validatePassword
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
    val isSuccess: Boolean = false,
    val emailError: EmailError? = null,
    val passwordError: PasswordError? = null,
    val confirmPasswordError: ConfirmPasswordError? = null
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
            if (validateData()){
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
    }

    fun validateData(): Boolean{
        val state = _uiState.value

        val emailError = validateEmail(state.email)
        val passwordError = validatePassword(state.password)
        val confirmPasswordError = validateConfirmPassword(state.password, state.repeatPassword)

        _uiState.update {
            it.copy(
                emailError = emailError,
                passwordError = passwordError,
                confirmPasswordError = confirmPasswordError
            )
        }

        return !(emailError != null || passwordError != null || confirmPasswordError != null)
    }

    fun onEmailChange(value: String){
        _uiState.update { state ->
            state.copy(
                email = value,
                emailError = if (state.emailError != null){
                    validateEmail(value)
                } else {
                    null
                }
            )
        }
    }

    fun onPasswordChange(value: String){
        _uiState.update { state ->
            state.copy(
                password = value,
                passwordError = if (state.passwordError != null){
                    validatePassword(value)
                } else {
                    null
                }
            )
        }
    }

    fun onUserNameChange(value: String){
        _uiState.update { it.copy(userName = value) }
    }

    fun onRepeatPasswordChange(value: String){
        _uiState.update { state ->
            state.copy(
                repeatPassword = value,
                confirmPasswordError = if (state.confirmPasswordError != null){
                    validateConfirmPassword(state.password, value)
                } else {
                    null
                }
            )
        }
    }
}