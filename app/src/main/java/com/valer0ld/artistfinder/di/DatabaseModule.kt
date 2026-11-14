package com.valer0ld.artistfinder.di

import android.content.Context
import androidx.room.Room
import com.valer0ld.artistfinder.data.local.db.AppDatabase
import com.valer0ld.artistfinder.data.local.db.user.CurrentUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "Artist_finder.db"
        ).build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): CurrentUserDao = db.CurrentUserDao()
}