package com.valer0ld.artistfinder.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val isLoggedIn: Flow<Boolean>
    suspend fun saveUserToken(token: String, userID: String)
    suspend fun getUserToken(email: String, password: String) : Pair<String?, String?>
    suspend fun createToken(email: String, password: String) : Pair<String, String>
    suspend fun logout()
}