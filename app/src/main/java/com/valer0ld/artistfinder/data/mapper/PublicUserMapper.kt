package com.valer0ld.artistfinder.data.mapper

import com.valer0ld.artistfinder.data.local.db.public_user.PublicUserEntity
import com.valer0ld.artistfinder.domain.model.RatingPlayer

fun RatingPlayer.toEntity() = PublicUserEntity(
    id = id,
    username = username,
    pictureUrl = pictureUrl,
    dailyGames = dailyGames,
    dailySteps = dailySteps,
    dailyTime = dailyTime,
    sumScore = sumScore,
    place = place
)

fun PublicUserEntity.toDomain() = RatingPlayer(
    id = id,
    username = username,
    pictureUrl = pictureUrl,
    dailyGames = dailyGames,
    dailySteps = dailySteps,
    dailyTime = dailyTime,
    sumScore = sumScore,
    place = place
)