package com.valer0ld.artistfinder.data.mapper

import com.valer0ld.artistfinder.data.local.db.user.CurrentUserEntity
import com.valer0ld.artistfinder.domain.model.CurrentUser

fun CurrentUser.toEntity() = CurrentUserEntity(
    id = id,
    userName = userName,
    email = email,
    avatarUrl = avatarUrl,
    gameStarts = gameStarts,
    gameEnds = gameEnds,
    totalTime = totalTime,
    totalSteps = totalSteps,
    currentRating = currentRating,
    bestRating = bestRating
)

fun CurrentUserEntity.toDomain() = CurrentUser(
    id = id,
    userName = userName,
    email = email,
    avatarUrl = avatarUrl,
    gameStarts = gameStarts,
    gameEnds = gameEnds,
    totalTime = totalTime,
    totalSteps = totalSteps,
    currentRating = currentRating,
    bestRating = bestRating
)