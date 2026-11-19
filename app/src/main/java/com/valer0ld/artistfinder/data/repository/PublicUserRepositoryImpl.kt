package com.valer0ld.artistfinder.data.repository

import com.valer0ld.artistfinder.data.local.datastore.AuthDataStore
import com.valer0ld.artistfinder.data.local.db.public_user.PublicUserDao
import com.valer0ld.artistfinder.data.mapper.toEntity
import com.valer0ld.artistfinder.data.mapper.toDomain
import com.valer0ld.artistfinder.domain.model.RatingPlayer
import com.valer0ld.artistfinder.domain.repository.PublicUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PublicUserRepositoryImpl @Inject constructor(
    private val publicUserDao: PublicUserDao,
    private val authDataStore: AuthDataStore
) : PublicUserRepository {

    override suspend fun getUser(id: Long): RatingPlayer? {
        return publicUserDao.getUser(id)?.toDomain()
    }

    override suspend fun observeUsers(): Flow<List<RatingPlayer>?> {
        return publicUserDao.observeUsers().map { list ->
            list?.map { it.toDomain()  }
        }
    }

    override suspend fun refreshUsers(users: List<RatingPlayer>) {
        publicUserDao.replaceAll(users.map { it.toEntity() })
    }

    override suspend fun observeUser(): Flow<RatingPlayer?> {
        return authDataStore.userID.flatMapLatest{ id ->
            if (id == null) flowOf(null)
            else publicUserDao.observeUser(id).map { it?.toDomain() }
        }
    }

}