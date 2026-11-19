package com.valer0ld.artistfinder.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val isLoggedIn: Flow<Boolean>
    suspend fun saveUserToken(token: String, userID: Long)
    suspend fun getUserToken(email: String, password: String) : Pair<String?, Long?>
    suspend fun createToken(email: String, password: String) : Pair<String, Long>
    suspend fun logout()
}