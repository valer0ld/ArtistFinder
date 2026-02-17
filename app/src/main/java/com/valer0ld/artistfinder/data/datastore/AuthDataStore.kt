package com.valer0ld.artistfinder.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class AuthDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        private val AUTH_TOKEN = stringPreferencesKey("auth_token")
        private val USER_ID = stringPreferencesKey("user_id")
    }

    val isLoggedIn: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }

    suspend fun saveLogin(token: String, userID: String){
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = true
            preferences[AUTH_TOKEN] = token
            preferences[USER_ID] = userID
        }
    }

    suspend fun logout(){
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun getToken(): String? {
        return dataStore.data.first() [AUTH_TOKEN]
    }
}
