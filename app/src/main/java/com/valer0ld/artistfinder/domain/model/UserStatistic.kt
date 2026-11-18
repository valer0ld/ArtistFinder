package com.valer0ld.artistfinder.domain.model

data class UserStatistic(
    val gameStarts: Int,
    val gameEnds: Int,
    val totalTime: Long,
    val totalSteps: Int,
    val dailyGameStarts: Int,
    val dailyGameEnds: Int,
    val dailyTime: Long,
    val dailySteps: Int,
    val bestRating: Int,
)