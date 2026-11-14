package com.valer0ld.artistfinder.domain.repository

import com.valer0ld.artistfinder.domain.model.CurrentUser
import kotlinx.coroutines.flow.Flow

interface CurrentUserRepository {
    fun observeUser(): Flow<CurrentUser?>
    suspend fun getUser(): CurrentUser?
    suspend fun upsertUser(user: CurrentUser)
    suspend fun refreshUser()
    suspend fun clearUser()
    suspend fun updateProfileInfo(userName: String, email: String, avatarUrl: String)
    suspend fun startGame()
    suspend fun addGameStats(duration: Long, steps: Int)
    suspend fun updateCurrentRating(rating: Int)
}