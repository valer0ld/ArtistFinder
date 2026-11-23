package com.valer0ld.artistfinder.data.repository

import android.app.LocaleManager
import android.content.Context
import android.os.LocaleList
import com.valer0ld.artistfinder.data.local.datastore.SettingsDataStore
import com.valer0ld.artistfinder.domain.model.AppLanguage
import com.valer0ld.artistfinder.domain.repository.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val settingsDataStore: SettingsDataStore
) : SettingsRepository {

    override val isDarkTheme: Flow<Boolean?> = settingsDataStore.isDarkTheme

    override val showInRating: Flow<Boolean> = settingsDataStore.showInRating

    private val localeManager = context.getSystemService(LocaleManager::class.java)

    override suspend fun getLanguage() : AppLanguage {
        val locales = localeManager.applicationLocales

        if (locales.isEmpty) return AppLanguage.SYSTEM

        return when (locales[0].language) {
            "ru" -> AppLanguage.RUSSIAN
            "en" -> AppLanguage.ENGLISH
            else -> AppLanguage.SYSTEM
        }
    }

    override suspend fun setLanguage(value: AppLanguage) {
        val locales = when (value) {
            AppLanguage.SYSTEM -> LocaleList.getEmptyLocaleList()
            AppLanguage.RUSSIAN -> LocaleList.forLanguageTags("ru")
            AppLanguage.ENGLISH -> LocaleList.forLanguageTags("en")
        }

        localeManager.applicationLocales = locales
    }

    override suspend fun setTheme(value: Boolean) {
        settingsDataStore.setTheme(value)
    }

    override suspend fun setShowInRating(value: Boolean) {
    settingsDataStore.setShowInRating(value)

        // отправка измененного состояние в бек
    }

}