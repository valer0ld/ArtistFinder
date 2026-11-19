package com.valer0ld.artistfinder.data.local.db.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameListDao {

    @Query("SELECT * FROM game_list WHERE id = :id LIMIT 1")
    suspend fun getGame(id: Long): GameListEntity?

    @Query("SELECT * FROM game_list")
    fun observeGames(): Flow<List<GameListEntity>>

    @Query("SELECT * FROM game_list")
    suspend fun getGames(): List<GameListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(game: GameListEntity)

    @Query("DELETE FROM game_list")
    suspend fun clear()
}