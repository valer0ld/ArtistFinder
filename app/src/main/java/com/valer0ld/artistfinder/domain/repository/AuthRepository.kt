package com.valer0ld.artistfinder.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val isLoggedIn: Flow<Boolean>
    suspend fun saveLogin(token: String, userId: String)
    suspend fun logout()
}