package com.valer0ld.artistfinder.data.local.db.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CurrentUserDao {

    @Query("SELECT * FROM current_user WHERE id = :id LIMIT 1")
    fun observeUser(id: String): Flow<CurrentUserEntity?>

    @Query("SELECT * FROM current_user WHERE id = :id LIMIT 1")
    suspend fun getUser(id: String): CurrentUserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: CurrentUserEntity)

    @Query("DELETE FROM current_user")
    suspend fun clear()

    @Query("""
        UPDATE current_user
        SET userName = :userName,
            email = :email,
            avatarUrl = :avatarUrl
        WHERE id = :id
    """)
    suspend fun updateProfileInfo(
        id: String,
        userName: String,
        email: String,
        avatarUrl: String
    )

    @Query("""
        UPDATE current_user
        SET gameStarts = gameStarts + 1
        WHERE id = :id
    """)
    suspend fun startGame(
        id: String
    )

    @Query("""
        UPDATE current_user
        SET gameEnds = gameEnds + 1,
            totalTime = totalTime + :duration,
            totalSteps = totalSteps + :steps
        WHERE id = :id
    """)
    suspend fun addGameStats(
        id: String,
        duration: Long,
        steps: Int
    )

    @Query("""
        UPDATE current_user
        SET bestRating = :rating
        WHERE id = :id
    """)
    suspend fun updateBestRating(
        id: String,
        rating: Int
    )

    @Query("""
        UPDATE current_user
        SET currentRating = :rating
        WHERE id = :id
    """)
    suspend fun updateCurrentRating(
        id: String,
        rating: Int
    )
}