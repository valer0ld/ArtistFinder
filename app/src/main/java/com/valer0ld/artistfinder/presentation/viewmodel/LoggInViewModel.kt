package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.model.CurrentUser
import com.valer0ld.artistfinder.domain.repository.AuthRepository
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import com.valer0ld.artistfinder.domain.validation.error_enums.EmailError
import com.valer0ld.artistfinder.domain.validation.error_enums.PasswordError
import com.valer0ld.artistfinder.domain.validation.validators.validateEmail
import com.valer0ld.artistfinder.domain.validation.validators.validatePassword
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
    val password: String = "",

    val emailError: EmailError? = null,
    val passwordError: PasswordError? = null
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
            if (validateData()){
                // где-то тут нужно обратиться к серверу и узнать, если ли такой аккаунт и соответствует ли пароль, после чего еще раз обновить состояние ошибок
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
    }

    fun validateData(): Boolean{
        val state = _uiState.value

        val emailError = validateEmail(state.email)
        val passwordError = validatePassword(state.password)

        _uiState.update {
            it.copy(
                emailError = emailError,
                passwordError = passwordError
            )
        }

        return !(emailError != null || passwordError != null)
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
}