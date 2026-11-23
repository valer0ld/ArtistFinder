package com.valer0ld.artistfinder.domain.repository

import com.valer0ld.artistfinder.domain.model.AppLanguage
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val isDarkTheme: Flow<Boolean?>
    val showInRating: Flow<Boolean>
    suspend fun setTheme(value: Boolean)
    suspend fun setLanguage(value: AppLanguage)
    suspend fun getLanguage() : AppLanguage
    suspend fun setShowInRating(value: Boolean)
}