package com.valer0ld.artistfinder.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Setting(
    val settingId: SettingId,
    val type: SettingType,
    val title: String,
    val icon: Int? = null,
    val color: SettingColor,
    val optionList: List<String>? = null
)

enum class SettingType {
    SWITCH,
    BUTTON,
    LIST,
    TEXTFIELD
}

enum class SettingId {
    DARK_THEME,
    LANGUAGE,
    SHOW_IN_RATING,
    EDIT_PROFILE,
    SAVE_PROFILE,
    EDIT_PICTURE,
    EDIT_USERNAME,
    EDIT_EMAIL,
    EDIT_PASSWORD,
    CONFIRM_PASSWORD,
    LOGOUT,
    DELETE_ACCOUNT
}

enum class SettingColor {
    DEFAULT,
    SECOND,
    DESTRUCTIVE
}

object SettingPreset{

    fun switchSetting(
        settingId: SettingId,
        title: String,
        icon: Int? = null,
        color: SettingColor = SettingColor.DEFAULT) = Setting(
            settingId = settingId,
            type = SettingType.SWITCH,
            title = title,
            icon = icon,
            color = color
        )

    fun buttonSetting(
        settingId: SettingId,
        title: String,
        icon: Int? = null,
        color: SettingColor = SettingColor.DEFAULT) = Setting(
        settingId = settingId,
        type = SettingType.BUTTON,
        title = title,
        icon = icon,
        color = color
    )

    fun listSetting(
        settingId: SettingId,
        title: String,
        icon: Int? = null,
        color: SettingColor = SettingColor.DEFAULT,
        list: List<String>) = Setting(
        settingId = settingId,
        type = SettingType.LIST,
        title = title,
        icon = icon,
        color = color,
        optionList = list
    )

    fun textFieldSetting(
        settingId: SettingId,
        title: String,
        icon: Int? = null,
        color: SettingColor = SettingColor.DEFAULT) = Setting(
        settingId = settingId,
        type = SettingType.TEXTFIELD,
        title = title,
        icon = icon,
        color = color
    )
}
