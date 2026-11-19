package com.valer0ld.artistfinder.domain.repository

import com.valer0ld.artistfinder.data.model.CurrentGame
import com.valer0ld.artistfinder.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameListRepository {
    suspend fun getGame(id: Long): Game?
    suspend fun observeGames(): Flow<List<Game>?>
    suspend fun getGames(): List<Game>?
    suspend fun upsert(game: CurrentGame)
    suspend fun clearGameList()
}