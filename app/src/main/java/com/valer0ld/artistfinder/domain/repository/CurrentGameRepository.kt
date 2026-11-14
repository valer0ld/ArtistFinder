package com.valer0ld.artistfinder.domain.repository

import com.valer0ld.artistfinder.data.model.CurrentGame
import com.valer0ld.artistfinder.domain.model.Artist
import kotlinx.coroutines.flow.StateFlow


interface CurrentGameRepository{
    val game: StateFlow<CurrentGame?>
    suspend fun startGame(start: Artist, target: Artist, isDaily: Boolean)
    suspend fun selectArtist(artist: Artist)
    suspend fun useReturn()
    suspend fun clear()
    suspend fun isWin() : Boolean
    suspend fun onWin(duration: Long) : CurrentGame
}