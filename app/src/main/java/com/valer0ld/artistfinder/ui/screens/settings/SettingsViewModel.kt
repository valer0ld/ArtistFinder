package com.valer0ld.artistfinder.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.repo.AuthRepository
import com.valer0ld.artistfinder.ui.models.SettingColor
import com.valer0ld.artistfinder.ui.models.SettingId
import com.valer0ld.artistfinder.ui.models.SettingPreset
import com.valer0ld.artistfinder.ui.models.SettingsHeader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    var isEditingProfile by mutableStateOf(false)

    fun onSettingAction(settingId: SettingId){
        when(settingId){
            SettingId.DARK_THEME -> {}
            SettingId.LANGUAGE -> {}
            SettingId.SHOW_IN_RATING -> {}
            SettingId.EDIT_PROFILE -> { isEditingProfile = true }
            SettingId.SAVE_PROFILE -> { isEditingProfile = false}
            SettingId.EDIT_PICTURE -> {}
            SettingId.EDIT_USERNAME -> {}
            SettingId.EDIT_EMAIL -> {}
            SettingId.EDIT_PASSWORD -> {}
            SettingId.CONFIRM_PASSWORD -> {}
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

    val settings = listOf(
        SettingsHeader(
            title = "Внешний вид",
            settingList = listOf(
                SettingPreset.switchSetting(
                    settingId = SettingId.DARK_THEME,
                    title = "Тёмная тема"),
                SettingPreset.listSetting(
                    settingId = SettingId.LANGUAGE,
                    title = "Язык",
                    list = listOf("Русский", "Английский"))
            )
        ),
        SettingsHeader(
            title = "Конфиденциональность",
            settingList = listOf(
                SettingPreset.switchSetting(
                    settingId = SettingId.SHOW_IN_RATING,
                    title = "Отображать в рейтинге"),
            )
        ),
        SettingsHeader(
            title = "Аккаунт",
            settingList = listOf(
                SettingPreset.buttonSetting(
                    settingId = SettingId.EDIT_PROFILE,
                    title = "Редактировать"
                ),
                SettingPreset.buttonSetting(
                    settingId = SettingId.SAVE_PROFILE,
                    title = "Сохранить"
                ),
                SettingPreset.buttonSetting(
                    settingId = SettingId.EDIT_PICTURE,
                    title = "Изменить аватар"
                ),
                SettingPreset.textFieldSetting(
                    settingId = SettingId.EDIT_USERNAME,
                    title = "Имя пользователя"
                ),
                SettingPreset.textFieldSetting(
                    settingId = SettingId.EDIT_EMAIL,
                    title = "Почта"
                ),
                SettingPreset.textFieldSetting(
                    settingId = SettingId.EDIT_PASSWORD,
                    title = "Пароль"
                ),
                SettingPreset.textFieldSetting(
                    settingId = SettingId.CONFIRM_PASSWORD,
                    title = "Подтвердить пароль"
                ),
                SettingPreset.buttonSetting(
                    settingId = SettingId.LOGOUT,
                    title = "Выход из аккаунта",
                    color = SettingColor.SECOND
                ),
                SettingPreset.buttonSetting(
                    settingId = SettingId.DELETE_ACCOUNT,
                    title = "Удалить аккаунт",
                    color = SettingColor.DESTRUCTIVE
                ),
            )
        )
    )
}