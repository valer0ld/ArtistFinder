package com.valer0ld.artistfinder.domain.model

import androidx.annotation.StringRes
import com.valer0ld.artistfinder.R

interface SettingsEnum{
    val displaySetting: Int
}

enum class AppLanguage(override val displaySetting: Int) : SettingsEnum {
    SYSTEM(R.string.language_system),
    RUSSIAN(R.string.language_russian),
    ENGLISH(R.string.language_english)
}