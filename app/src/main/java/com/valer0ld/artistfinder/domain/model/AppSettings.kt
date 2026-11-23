package com.valer0ld.artistfinder.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.valer0ld.artistfinder.R

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
            title = R.string.appearance,
            settingList = listOf(
                SettingPreset.switchSetting(
                    settingId = SettingId.DARK_THEME,
                    title = R.string.dark_theme
                ),
                SettingPreset.listSetting(
                    settingId = SettingId.LANGUAGE,
                    title = R.string.language,
                    list = listOf(AppLanguage.SYSTEM, AppLanguage.ENGLISH, AppLanguage.RUSSIAN)
                )
            )
        ),
        SettingsHeader(
            title = R.string.confidenciality,
            settingList = listOf(
                SettingPreset.switchSetting(
                    settingId = SettingId.SHOW_IN_RATING,
                    title = R.string.show_in_rating
                ),
            )
        ),
        SettingsHeader(
            title = R.string.account,
            settingList = listOf(
                SettingPreset.buttonSetting(
                    settingId = SettingId.EDIT_PROFILE,
                    title = R.string.edit
                ),
                SettingPreset.buttonSetting(
                    settingId = SettingId.SAVE_PROFILE,
                    title = R.string.save
                ),
                SettingPreset.buttonSetting(
                    settingId = SettingId.QUIT_EDITING,
                    title = R.string.cancel,
                    color = SettingColor.DESTRUCTIVE
                ),
                SettingPreset.buttonSetting(
                    settingId = SettingId.EDIT_PICTURE,
                    title = R.string.change_avatar
                ),
                SettingPreset.textFieldSetting(
                    settingId = SettingId.EDIT_USERNAME,
                    title = R.string.username
                ),
                SettingPreset.textFieldSetting(
                    settingId = SettingId.EDIT_EMAIL,
                    title = R.string.email
                ),
                SettingPreset.textFieldSetting(
                    settingId = SettingId.EDIT_PASSWORD,
                    title = R.string.password
                ),
                SettingPreset.textFieldSetting(
                    settingId = SettingId.CONFIRM_PASSWORD,
                    title = R.string.repeat_password
                ),
                SettingPreset.buttonSetting(
                    settingId = SettingId.LOGOUT,
                    title = R.string.log_out,
                    color = SettingColor.SECOND
                ),
                SettingPreset.buttonSetting(
                    settingId = SettingId.DELETE_ACCOUNT,
                    title = R.string.delete_account,
                    color = SettingColor.DESTRUCTIVE
                ),
            )
        )
    )
}
