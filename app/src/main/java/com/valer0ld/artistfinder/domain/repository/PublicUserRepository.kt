package com.valer0ld.artistfinder.domain.repository

import com.valer0ld.artistfinder.domain.model.RatingPlayer
import kotlinx.coroutines.flow.Flow

interface PublicUserRepository {
    suspend fun getUser(id: Long): RatingPlayer?
    suspend fun observeUsers(): Flow<List<RatingPlayer>?>
    suspend fun refreshUsers(users: List<RatingPlayer>)
    suspend fun observeUser(): Flow<RatingPlayer?>
}