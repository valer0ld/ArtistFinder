package com.valer0ld.artistfinder.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.model.AppLanguage
import com.valer0ld.artistfinder.domain.model.SettingId
import com.valer0ld.artistfinder.domain.repository.AuthRepository
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import com.valer0ld.artistfinder.domain.repository.GameListRepository
import com.valer0ld.artistfinder.domain.repository.SettingsRepository
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
import kotlin.Boolean

data class SettingsUIState(
    val values: Map<SettingId, Any?> = mapOf(
        SettingId.DARK_THEME to false,
        SettingId.LANGUAGE to AppLanguage.SYSTEM,
        SettingId.SHOW_IN_RATING to true,
        SettingId.EDIT_USERNAME to  "",
        SettingId.EDIT_EMAIL to "",
        SettingId.EDIT_PASSWORD to "",
        SettingId.CONFIRM_PASSWORD to "",
        SettingId.EDIT_PICTURE to ""
    ),
    val oldUserInfo: Map<SettingId, Any?> = mapOf(
        SettingId.EDIT_USERNAME to  "",
        SettingId.EDIT_EMAIL to "",
        SettingId.EDIT_PASSWORD to "",
        SettingId.CONFIRM_PASSWORD to "",
        SettingId.EDIT_PICTURE to ""
    ),
    val isEditingProfile: Boolean = false,
    val emailError: EmailError? = null,
    val passwordError: PasswordError? = null,
    val confirmPasswordError: ConfirmPasswordError? = null
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val currentUserRepository: CurrentUserRepository,
    private val settingsRepository: SettingsRepository,
    private val gameListRepository: GameListRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUIState())
    val uiState: StateFlow<SettingsUIState> = _uiState.asStateFlow()

    init{
        loadUserInfo()
        loadThemeMod()
        loadLanguage()
        loadShowInRating()
    }

    fun loadUserInfo(){
        viewModelScope.launch {
            currentUserRepository.observeUser().collect { user ->
                if (user == null) return@collect

                val newValues = mapOf(
                    SettingId.EDIT_USERNAME to  user.userName,
                    SettingId.EDIT_EMAIL to user.email,
                    SettingId.EDIT_PICTURE to user.avatarUrl
                )

                _uiState.update { state ->
                    state.copy(
                        values = state.values + newValues,
                        oldUserInfo = newValues.filterKeys { it in state.oldUserInfo }
                    )
                }
            }
        }
    }

    fun loadThemeMod(){
        viewModelScope.launch {
            settingsRepository.isDarkTheme.collect { value ->
                if (value == null) return@collect

                _uiState.update { state ->
                    state.copy(
                        values = state.values + (SettingId.DARK_THEME to value)
                    )
                }
            }
        }
    }

    fun loadLanguage(){
        viewModelScope.launch {
            val language = settingsRepository.getLanguage()

            _uiState.update { state ->
                state.copy(
                    values = state.values + (SettingId.LANGUAGE to language)
                )
            }
        }
    }

    fun loadShowInRating(){
        viewModelScope.launch {
            settingsRepository.showInRating.collect { value ->
                _uiState.update { state ->
                    state.copy(
                        values =state.values + (SettingId.SHOW_IN_RATING to value)
                    )
                }
            }
        }
    }

    fun validateData(): Boolean{
        val state = _uiState.value

        val emailError = validateEmail(state.values[SettingId.EDIT_EMAIL] as String)
        val passwordError = validatePassword(state.values[SettingId.EDIT_PASSWORD] as String)
        val confirmPasswordError = validateConfirmPassword(state.values[SettingId.EDIT_PASSWORD] as String, state.values[SettingId.CONFIRM_PASSWORD] as String)

        _uiState.update {
            it.copy(
                emailError = emailError,
                passwordError = passwordError,
                confirmPasswordError = confirmPasswordError
            )
        }

        return !(emailError != null || passwordError != null || confirmPasswordError != null)
    }

    fun onSettingAction(settingId: SettingId, value: Any? = null){
        when(settingId){
            SettingId.DARK_THEME -> {
                viewModelScope.launch {
                    settingsRepository.setTheme(value as Boolean)
                    _uiState.update {it.copy(values = it.values + (SettingId.DARK_THEME to value) )}
                }

            }
            SettingId.LANGUAGE -> {
                viewModelScope.launch {
                    val language = AppLanguage.entries.first{
                        it == value
                    }
                    settingsRepository.setLanguage(language)
                    _uiState.update {it.copy(values = it.values + (SettingId.LANGUAGE to value) )}
                }

            }
            SettingId.SHOW_IN_RATING -> {
                viewModelScope.launch {
                    settingsRepository.setShowInRating(value as Boolean)
                    _uiState.update {it.copy(values = it.values + (SettingId.SHOW_IN_RATING to value) )}
                }

            }
            SettingId.EDIT_PROFILE -> {
                _uiState.update { state ->
                    state.copy(
                    isEditingProfile = true,
                    )
                }
            }
            SettingId.QUIT_EDITING -> {
                _uiState.update { state ->
                    state.copy(
                        isEditingProfile = false,
                        values = state.values + state.oldUserInfo, // почему-то не возвращает пароль и подтверждение пароля
                        emailError = null,
                        passwordError = null,
                        confirmPasswordError = null
                    )
                }
            }
            SettingId.SAVE_PROFILE -> {

                viewModelScope.launch {
                    if (validateData()){
                        currentUserRepository.updateProfileInfo(
                            userName = _uiState.value.values[SettingId.EDIT_USERNAME] as String,
                            email = _uiState.value.values[SettingId.EDIT_EMAIL] as String,
                            avatarUrl = _uiState.value.values[SettingId.EDIT_PICTURE] as String
                        )
                        _uiState.update { state ->
                            state.copy(
                                isEditingProfile = false,
                                oldUserInfo = state.values.filterKeys { it in state.oldUserInfo.keys } // перестраховка
                            )
                        }
                    }
                }

            }
            SettingId.EDIT_PICTURE -> { _uiState.update {it.copy(values = it.values + (SettingId.EDIT_PICTURE to "") )}}
            SettingId.EDIT_USERNAME -> {  _uiState.update {it.copy(values = it.values + (SettingId.EDIT_USERNAME to value) )} }
            SettingId.EDIT_EMAIL -> {
                _uiState.update { state ->
                    state.copy(
                        values = state.values + (SettingId.EDIT_EMAIL to value),
                        emailError = if (state.emailError != null){
                            validateEmail(value as String)
                        } else {
                            null
                        }
                    )
                }
            }
            SettingId.EDIT_PASSWORD -> {
                _uiState.update { state ->
                    state.copy(
                        values = state.values + (SettingId.EDIT_PASSWORD to value),
                        passwordError = if (state.passwordError != null){
                            validatePassword(value as String)
                        } else {
                            null
                        }
                    )
                }
            }
            SettingId.CONFIRM_PASSWORD -> {
                _uiState.update { state ->
                    state.copy(
                        values = state.values + (SettingId.CONFIRM_PASSWORD to value),
                        confirmPasswordError = if (state.confirmPasswordError != null){
                            validateConfirmPassword(state.values[SettingId.EDIT_PASSWORD] as String, value as String)
                        } else {
                            null
                        }
                    )
                }
                _uiState.update {it.copy( )}
            }
            SettingId.LOGOUT -> {
                viewModelScope.launch {
                    gameListRepository.clearGameList()
                    currentUserRepository.clearUser()
                    authRepository.logout()
                }
            }
            SettingId.DELETE_ACCOUNT -> {
                viewModelScope.launch {
                    gameListRepository.clearGameList()
                    currentUserRepository.clearUser()
                    authRepository.logout()
                }
            }
        }
    }
}