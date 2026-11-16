package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.domain.model.SettingId
import com.valer0ld.artistfinder.domain.repository.AuthRepository
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
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
    val isEditingProfile: Boolean = false
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val currentUserRepository: CurrentUserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUIState())
    val uiState: StateFlow<SettingsUIState> = _uiState.asStateFlow()

    init{
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

    fun onSettingAction(settingId: SettingId, value: Any? = null){
        when(settingId){
            SettingId.DARK_THEME -> { _uiState.update {it.copy(values = it.values + (SettingId.DARK_THEME to value) )} }
            SettingId.LANGUAGE -> {  _uiState.update {it.copy(values = it.values + (SettingId.LANGUAGE to value) )} }
            SettingId.SHOW_IN_RATING -> {  _uiState.update {it.copy(values = it.values + (SettingId.SHOW_IN_RATING to value) )} }
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
                        //values = state.values + state.oldUserInfo.filterKeys { it in state.values }
                        values = state.values + state.oldUserInfo
                    )
                }
            }
            SettingId.SAVE_PROFILE -> {

                viewModelScope.launch {
                    if (_uiState.value.values[SettingId.EDIT_PASSWORD] != _uiState.value.oldUserInfo[SettingId.EDIT_PASSWORD]){
                        if (_uiState.value.values[SettingId.CONFIRM_PASSWORD] == _uiState.value.values[SettingId.EDIT_PASSWORD]){
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

                        //else{ ошибка: не совпадают пароли }
                    }
                    else{
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
            SettingId.EDIT_EMAIL -> {  _uiState.update {it.copy(values = it.values + (SettingId.EDIT_EMAIL to value) )} }
            SettingId.EDIT_PASSWORD -> {  _uiState.update {it.copy(values = it.values + (SettingId.EDIT_PASSWORD to value) )} }
            SettingId.CONFIRM_PASSWORD -> { _uiState.update {it.copy(values = it.values + (SettingId.CONFIRM_PASSWORD to value) )} }
            SettingId.LOGOUT -> {
                viewModelScope.launch {
                    currentUserRepository.clearUser()
                    authRepository.logout()
                }
            }
            SettingId.DELETE_ACCOUNT -> {
                viewModelScope.launch {
                    currentUserRepository.clearUser()
                    authRepository.logout()
                }
            }
        }
    }
}