package com.valer0ld.artistfinder.data.repository

import com.valer0ld.artistfinder.data.datastore.AuthDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val  authDataStore: AuthDataStore
) {
    val isLoggedIn: Flow<Boolean> = authDataStore.isLoggedIn

    suspend fun saveLogin(token: String, userId: String){
        authDataStore.saveLogin(token, userId)
    }

    suspend fun logout(){
        authDataStore.logout()
    }
}