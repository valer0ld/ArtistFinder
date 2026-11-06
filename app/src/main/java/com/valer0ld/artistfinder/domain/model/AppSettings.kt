package com.valer0ld.artistfinder.domain.model

import com.valer0ld.artistfinder.presentation.model.SettingsHeader

enum class SettingId {
    DARK_THEME,
    LANGUAGE,
    SHOW_IN_RATING,
    EDIT_PROFILE,
    SAVE_PROFILE,
    QUIT_EDITING,
    EDIT_PICTURE,
    EDIT_USERNAME,
    EDIT_EMAIL,
    EDIT_PASSWORD,
    CONFIRM_PASSWORD,
    LOGOUT,
    DELETE_ACCOUNT
}

object AppSettings{
    val list = listOf(
        SettingsHeader(
            title = "Внешний вид",
            settingList = listOf(
                SettingPreset.switchSetting(
                    settingId = SettingId.DARK_THEME,
                    title = "Тёмная тема"
                ),
                SettingPreset.listSetting(
                    settingId = SettingId.LANGUAGE,
                    title = "Язык",
                    list = listOf("Русский", "Английский")
                )
            )
        ),
        SettingsHeader(
            title = "Конфиденциональность",
            settingList = listOf(
                SettingPreset.switchSetting(
                    settingId = SettingId.SHOW_IN_RATING,
                    title = "Отображать в рейтинге"
                ),
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
                    settingId = SettingId.QUIT_EDITING,
                    title = "Отменить",
                    color = SettingColor.DESTRUCTIVE
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
