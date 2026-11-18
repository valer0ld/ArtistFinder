package com.valer0ld.artistfinder.data.repository

import com.valer0ld.artistfinder.data.local.db.game.GameListDao
import com.valer0ld.artistfinder.data.mapper.toGame
import com.valer0ld.artistfinder.data.mapper.toGameListEntity
import com.valer0ld.artistfinder.data.model.CurrentGame
import com.valer0ld.artistfinder.domain.model.Game
import com.valer0ld.artistfinder.domain.repository.GameListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GameListRepositoryImpl @Inject constructor(
    private val gameListDao: GameListDao
) : GameListRepository{

    override suspend fun getGame(id: Long): Game? {
        return gameListDao.getGame(id)?.toGame()
    }

    override suspend fun observeGames(): Flow<List<Game>?>{
        return gameListDao.observeGames().map { list ->
            list.map { it.toGame() }
        }
    }

    override suspend fun getGames(): List<Game> {
        return gameListDao.getGames().map { it.toGame() }
    }

    override suspend fun upsert(game: CurrentGame){
        gameListDao.upsert(game.toGameListEntity())
    }

    override suspend fun clearGameList(){
        gameListDao.clear()
    }
}