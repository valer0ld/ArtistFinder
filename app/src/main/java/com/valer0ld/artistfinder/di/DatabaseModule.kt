package com.valer0ld.artistfinder.di

import android.content.Context
import androidx.room.Room
import com.valer0ld.artistfinder.data.local.db.AppDatabase
import com.valer0ld.artistfinder.data.local.db.game.GameListDao
import com.valer0ld.artistfinder.data.local.db.current_user.CurrentUserDao
import com.valer0ld.artistfinder.data.local.db.public_user.PublicUserDao
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
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    fun provideCurrentUserDao(db: AppDatabase): CurrentUserDao = db.CurrentUserDao()

    @Provides
    fun provideGameListDao(db: AppDatabase): GameListDao = db.GameListDao()

    @Provides
    fun providePublicUserDao(db: AppDatabase): PublicUserDao = db.PublicUserDao()
}