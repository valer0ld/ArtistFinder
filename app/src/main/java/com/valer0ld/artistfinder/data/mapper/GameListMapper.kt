package com.valer0ld.artistfinder.data.mapper

import com.valer0ld.artistfinder.data.local.db.game.GameListEntity
import com.valer0ld.artistfinder.data.model.CurrentGame
import com.valer0ld.artistfinder.domain.model.Game

fun GameListEntity.toDomain() = Game(
    id = id,
    start = start,
    target = target,
    duration = duration,
    stepCounter = stepCounter,
    steps = steps,
    isDaily = isDaily,
    isFinished = isFinished
)

fun CurrentGame.toEntity() = GameListEntity(
    start = steps.first(),
    target = targetArtist,
    duration = duration,
    stepCounter = currentStep,
    steps = steps,
    isDaily = isDaily,
    isFinished = isFinished
)