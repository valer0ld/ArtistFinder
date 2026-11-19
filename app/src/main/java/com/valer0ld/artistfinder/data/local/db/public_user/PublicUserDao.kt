package com.valer0ld.artistfinder.data.local.db.public_user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow


@Dao
interface PublicUserDao {

    @Query("SELECT * FROM public_user WHERE id = :id LIMIT 1")
    fun observeUser(id: Long): Flow<PublicUserEntity?>

    @Query("SELECT * FROM public_user WHERE id = :id LIMIT 1")
    suspend fun getUser(id: Long): PublicUserEntity?

    @Query("SELECT * FROM public_user ORDER BY place DESC")
    fun observeUsers(): Flow<List<PublicUserEntity>?>

    @Query("SELECT * FROM public_user  ORDER BY place DESC")
    suspend fun getUsers(): List<PublicUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PublicUserEntity>)

    @Query("DELETE FROM public_user")
    suspend fun clear()

    @Transaction
    suspend fun replaceAll(users: List<PublicUserEntity>) {
        clear()
        insertAll(users)
    }
}