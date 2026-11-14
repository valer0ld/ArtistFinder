package com.valer0ld.artistfinder.domain.model

data class UserStatistic(
    var gameStarts: Int,
    var gameEnds: Int,
    var totalTime: Long,
    var totalSteps: Int,
    var bestRating: Int
)