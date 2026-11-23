package com.valer0ld.artistfinder.domain.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class Setting(
    val Id: SettingId,
    val type: SettingType,
    @StringRes val title: Int,
    val icon: Int? = null,
    val color: SettingColor,
    val optionList: List<SettingsEnum>? = null
)

enum class SettingType {
    SWITCH,
    BUTTON,
    LIST,
    TEXTFIELD
}

enum class SettingColor {
    DEFAULT,
    SECOND,
    DESTRUCTIVE
}

object SettingPreset{

    fun switchSetting(
        settingId: SettingId,
        title: Int,
        icon: Int? = null,
        color: SettingColor = SettingColor.DEFAULT) = Setting(
            Id = settingId,
            type = SettingType.SWITCH,
            title = title,
            icon = icon,
            color = color
        )

    fun buttonSetting(
        settingId: SettingId,
        title: Int,
        icon: Int? = null,
        color: SettingColor = SettingColor.DEFAULT) = Setting(
        Id = settingId,
        type = SettingType.BUTTON,
        title = title,
        icon = icon,
        color = color
    )

    fun listSetting(
        settingId: SettingId,
        title: Int,
        icon: Int? = null,
        color: SettingColor = SettingColor.DEFAULT,
        list: List<SettingsEnum>) = Setting(
        Id = settingId,
        type = SettingType.LIST,
        title = title,
        icon = icon,
        color = color,
        optionList = list
    )

    fun textFieldSetting(
        settingId: SettingId,
        title: Int,
        icon: Int? = null,
        color: SettingColor = SettingColor.DEFAULT) = Setting(
        Id = settingId,
        type = SettingType.TEXTFIELD,
        title = title,
        icon = icon,
        color = color
    )
}