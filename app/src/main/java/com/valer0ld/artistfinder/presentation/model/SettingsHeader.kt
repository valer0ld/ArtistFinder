package com.valer0ld.artistfinder.presentation.model

import androidx.compose.runtime.Immutable
import com.valer0ld.artistfinder.domain.model.Setting

@Immutable
data class SettingsHeader (
    val title: String,
    val icon: Int? = null,
    val settingList: List<Setting>
)
