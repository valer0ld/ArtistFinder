package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.data.repository.AuthRepository
import com.valer0ld.artistfinder.domain.model.SettingId
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
        SettingId.LANGUAGE to "Русский",
        SettingId.SHOW_IN_RATING to true,
        SettingId.EDIT_USERNAME to  "Имя пользователя",
        SettingId.EDIT_EMAIL to "почта",
        SettingId.EDIT_PASSWORD to "пароль",
        SettingId.CONFIRM_PASSWORD to "пароль"
    ),
    val isEditingProfile: Boolean = false,
    val userPicture: Int = R.drawable.webp_user_icon_black,
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUIState())
    val uiState: StateFlow<SettingsUIState> = _uiState.asStateFlow()

    fun onSettingAction(settingId: SettingId, value: Any? = null){
        when(settingId){
            SettingId.DARK_THEME -> { _uiState.update {it.copy(values = it.values + (SettingId.DARK_THEME to value) )} }
            SettingId.LANGUAGE -> {  _uiState.update {it.copy(values = it.values + (SettingId.LANGUAGE to value) )} }
            SettingId.SHOW_IN_RATING -> {  _uiState.update {it.copy(values = it.values + (SettingId.SHOW_IN_RATING to value) )} }
            SettingId.EDIT_PROFILE -> { _uiState.update {it.copy(isEditingProfile = true )} }
            SettingId.SAVE_PROFILE -> { _uiState.update {it.copy(isEditingProfile = false )} }
            SettingId.EDIT_PICTURE -> { _uiState.update {it.copy(values = it.values + (SettingId.EDIT_PICTURE to value) )}}
            SettingId.EDIT_USERNAME -> {  _uiState.update {it.copy(values = it.values + (SettingId.EDIT_USERNAME to value) )} }
            SettingId.EDIT_EMAIL -> {  _uiState.update {it.copy(values = it.values + (SettingId.EDIT_EMAIL to value) )} }
            SettingId.EDIT_PASSWORD -> {  _uiState.update {it.copy(values = it.values + (SettingId.EDIT_PASSWORD to value) )} }
            SettingId.CONFIRM_PASSWORD -> {
                if (value == _uiState.value.values[SettingId.EDIT_PASSWORD])
                    _uiState.update {it.copy(values = it.values + (SettingId.CONFIRM_PASSWORD to value) )}
            }
            SettingId.LOGOUT -> {
                viewModelScope.launch {
                    authRepository.logout()
                }
            }
            SettingId.DELETE_ACCOUNT -> {
                viewModelScope.launch {
                    authRepository.logout()
                }
            }
        }
    }
}