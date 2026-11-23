package com.valer0ld.artistfinder.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.valer0ld.artistfinder.di.qualifiers.SettingsDataStore
import com.valer0ld.artistfinder.domain.model.AppLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsDataStore @Inject constructor(
    @SettingsDataStore
    private val dataStore: DataStore<Preferences>
) {
    companion object{
        private val IS_DARK_THEME = booleanPreferencesKey("app_theme")
        private val LANGUAGE = stringPreferencesKey("language")
        private val SHOW_IN_RATING = booleanPreferencesKey("show_in_rating")
    }

    val isDarkTheme: Flow<Boolean?> = dataStore.data
        .catch { exception ->
        if (exception is IOException) emit(emptyPreferences())
        else throw  exception
        }.map { preferences ->
            preferences[IS_DARK_THEME]
        }

    val language: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { preferences ->
            preferences[LANGUAGE] ?: AppLanguage.SYSTEM.name
        }

    val showInRating: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw  exception
        }.map { preferences ->
            preferences[SHOW_IN_RATING] ?: true
        }

    suspend fun setTheme(value: Boolean){
        dataStore.edit { it[IS_DARK_THEME] = value }
    }

    suspend fun setLanguage(value: String){
        dataStore.edit { it[LANGUAGE] = value }
    }

    suspend fun setShowInRating(value: Boolean){
        dataStore.edit { it[SHOW_IN_RATING] = value }
    }
}