package com.valer0ld.artistfinder.data.repository

import com.valer0ld.artistfinder.data.local.datastore.AuthDataStore
import com.valer0ld.artistfinder.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val  authDataStore: AuthDataStore
) : AuthRepository {

    override val isLoggedIn: Flow<Boolean> = authDataStore.isLoggedIn

    override suspend fun saveUserToken(token: String, userID: Long){
        authDataStore.saveUserToken(token, userID)
    }

    override suspend fun getUserToken(email: String, password: String) : Pair<String?, Long?> {
        return if (password != "" && email != ""){
            authDataStore.getToken() to authDataStore.getUserID()
        } else null to null
    }

    override suspend fun createToken(email: String, password: String) : Pair<String, Long> {
        return "1" to 1L
    }

    override suspend fun logout(){
        authDataStore.logout()
    }
}