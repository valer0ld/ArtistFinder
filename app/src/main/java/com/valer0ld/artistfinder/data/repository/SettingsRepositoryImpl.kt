package com.valer0ld.artistfinder.data.repository

import android.content.Context
import com.valer0ld.artistfinder.data.local.datastore.SettingsDataStore
import com.valer0ld.artistfinder.domain.model.AppLanguage
import com.valer0ld.artistfinder.domain.repository.SettingsRepository
import com.valer0ld.artistfinder.presentation.util.applyLanguage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val settingsDataStore: SettingsDataStore
) : SettingsRepository {

    override val isDarkTheme: Flow<Boolean?> = settingsDataStore.isDarkTheme

    override val language: Flow<AppLanguage> = settingsDataStore.language.map { value ->
        AppLanguage.entries.firstOrNull{
            it.name == value
        } ?: AppLanguage.SYSTEM
    }

    override val showInRating: Flow<Boolean> = settingsDataStore.showInRating

    override suspend fun setTheme(value: Boolean) {
        settingsDataStore.setTheme(value)
    }

    override suspend fun setLanguage(value: AppLanguage) {
        settingsDataStore.setLanguage(value.name)
        context.applyLanguage(value)
    }

    override suspend fun setShowInRating(value: Boolean) {
    settingsDataStore.setShowInRating(value)

        // отправка измененного состояние в бек
    }

}