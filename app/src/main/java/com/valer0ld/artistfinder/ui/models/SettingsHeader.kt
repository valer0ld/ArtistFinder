package com.valer0ld.artistfinder.ui.models

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsHeader (
    val title: String,
    val icon: Int? = null,
    val settingList: List<Setting>
)
