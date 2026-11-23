package com.valer0ld.artistfinder.di

import com.valer0ld.artistfinder.data.repository.AuthRepositoryImpl
import com.valer0ld.artistfinder.data.repository.CurrentGameRepositoryImpl
import com.valer0ld.artistfinder.data.repository.CurrentUserRepositoryImpl
import com.valer0ld.artistfinder.data.repository.GameListRepositoryImpl
import com.valer0ld.artistfinder.data.repository.PublicUserRepositoryImpl
import com.valer0ld.artistfinder.data.repository.SettingsRepositoryImpl
import com.valer0ld.artistfinder.domain.repository.AuthRepository
import com.valer0ld.artistfinder.domain.repository.CurrentGameRepository
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import com.valer0ld.artistfinder.domain.repository.GameListRepository
import com.valer0ld.artistfinder.domain.repository.PublicUserRepository
import com.valer0ld.artistfinder.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ) : AuthRepository

    @Binds
    @Singleton
    abstract fun bindCurrentGameRepository(
        impl: CurrentGameRepositoryImpl
    ) : CurrentGameRepository

    @Binds
    @Singleton
    abstract fun bindCurrentUserRepository(
        impl: CurrentUserRepositoryImpl
    ) : CurrentUserRepository

    @Binds
    @Singleton
    abstract fun bindGameListRepository(
        impl: GameListRepositoryImpl
    ) : GameListRepository

    @Binds
    @Singleton
    abstract fun bindPublicUserRepository(
        impl: PublicUserRepositoryImpl
    ) : PublicUserRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(
        impl: SettingsRepositoryImpl
    ) : SettingsRepository
}