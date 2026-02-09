package com.valer0ld.artistfinder.ui.models

data class Setting(
    val type: SettingType,
    val title: String,
    val icon: Int?,
    val optionList: List<String>?,
    val onSettingActivate: ()->Unit
)

enum class SettingType {
    SWITCH,
    BUTTON,
    LIST,
    TEXTFIELD
}
