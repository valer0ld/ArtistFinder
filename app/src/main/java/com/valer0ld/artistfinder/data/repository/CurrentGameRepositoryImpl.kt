package com.valer0ld.artistfinder.data.repository

import com.valer0ld.artistfinder.data.model.CurrentGame
import com.valer0ld.artistfinder.domain.model.Artist
import com.valer0ld.artistfinder.domain.repository.CurrentGameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentGameRepositoryImpl @Inject constructor() : CurrentGameRepository{
    private val _game = MutableStateFlow<CurrentGame?>(null)
    override val game: StateFlow<CurrentGame?> = _game.asStateFlow()

    override suspend fun startGame(start: Artist, target: Artist, isDaily: Boolean) {
        _game.value = CurrentGame(
            currentArtist = start,
            targetArtist = target,
            steps = listOf(start),
            isDaily = isDaily
        )
    }

    override suspend fun selectArtist(artist: Artist) {
        _game.update { current ->
            current?.copy(
                currentArtist = artist,
                steps = current.steps + artist,
                currentStep = current.currentStep + 1,
            )
        }
    }

    override suspend fun useReturn() {
        _game.update{ current ->
            current?.copy(
                currentArtist = current.steps[current.steps.size-2] ?: Artist("ошибка", 0),
                steps = current.steps.subList(0, toIndex = current.steps.size - 2),
                currentStep = current.currentStep - 1,
                currentReturns = current.currentReturns - 1
            )
        }
    }

    override suspend fun clear() {
        _game.value = null
    }

    override suspend fun isWin() : Boolean {
        return game.value?.currentArtist == _game.value?.targetArtist
    }

    override suspend fun onWin(duration: Long) {
        _game.update{ current ->
            current?.copy(
                duration = duration,
                isFinished = true
            )
        }
    }
}