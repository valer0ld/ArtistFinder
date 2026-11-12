package com.valer0ld.artistfinder.data.repository

import com.valer0ld.artistfinder.data.datastore.AuthDataStore
import com.valer0ld.artistfinder.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val  authDataStore: AuthDataStore
) : AuthRepository {

    override val isLoggedIn: Flow<Boolean> = authDataStore.isLoggedIn

    override suspend fun saveLogin(token: String, userId: String){
        authDataStore.saveLogin(token, userId)
    }

    override suspend fun logout(){
        authDataStore.logout()
    }
}