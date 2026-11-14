package com.valer0ld.artistfinder.data.repository

import com.valer0ld.artistfinder.data.local.datastore.AuthDataStore
import com.valer0ld.artistfinder.data.local.db.user.CurrentUserDao
import com.valer0ld.artistfinder.data.mapper.toDomain
import com.valer0ld.artistfinder.data.mapper.toEntity
import com.valer0ld.artistfinder.domain.model.CurrentUser
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrentUserRepositoryImpl @Inject constructor(
    private val currentUserDao: CurrentUserDao,
    private val authDataStore: AuthDataStore
) : CurrentUserRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun observeUser(): Flow<CurrentUser?>{
        return authDataStore.userID.flatMapLatest{ id ->
            if (id.isNullOrBlank()) flowOf(null)
            else currentUserDao.observeUser(id).map { it?.toDomain() }
        }
    }

    override suspend fun getUser(): CurrentUser?{
        val id = authDataStore.getUserID() ?: return null
        return currentUserDao.getUser(id)?.toDomain()
    }

    override suspend fun refreshUser(){
        val id = authDataStore.getUserID() ?: return

        // обновляем из апи
    }

    override suspend fun clearUser(){
        currentUserDao.clear()
    }

    override suspend fun upsertUser(user: CurrentUser){
        currentUserDao.upsert(user.toEntity())
    }

    override suspend fun updateProfileInfo(
        userName: String,
        email: String,
        avatarUrl: String
    ) {
        val id = authDataStore.getUserID() ?: return
        currentUserDao.updateProfileInfo(id, userName, email, avatarUrl)
    }

    override suspend fun startGame() {
        val id = authDataStore.getUserID() ?: return
        currentUserDao.startGame(id)
    }

    override suspend fun addGameStats(duration: Long, steps: Int) {
        val id = authDataStore.getUserID() ?: return
        currentUserDao.addGameStats(id, duration, steps)
    }

    override suspend fun updateCurrentRating(rating: Int) {
        val id = authDataStore.getUserID() ?: return
        currentUserDao.updateCurrentRating(id, rating)

        val currentUserBestRating = currentUserDao.getUser(id)?.bestRating
        if (currentUserBestRating != null && currentUserBestRating != 0 && currentUserBestRating > rating){
            currentUserDao.updateBestRating(id, rating)
        }
    }

}