package com.valer0ld.artistfinder.domain.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class SettingsHeader (
    @StringRes val title: Int,
    val icon: Int? = null,
    val settingList: List<Setting>
)
