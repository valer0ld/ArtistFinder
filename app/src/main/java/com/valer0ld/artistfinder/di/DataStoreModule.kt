package com.valer0ld.artistfinder.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.valer0ld.artistfinder.data.datastore.AuthDataStore
import com.valer0ld.artistfinder.repo.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authDataStore: AuthDataStore
    ) : AuthRepository {
        return AuthRepository(authDataStore)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences>{
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("auth_prefs")
        }
    }

}