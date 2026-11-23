package com.valer0ld.artistfinder.domain.repository

import com.valer0ld.artistfinder.domain.model.AppLanguage
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val isDarkTheme: Flow<Boolean?>
    val language: Flow<AppLanguage>
    val showInRating: Flow<Boolean>
    suspend fun setTheme(value: Boolean)
    suspend fun setLanguage(value: AppLanguage)
    suspend fun setShowInRating(value: Boolean)
}