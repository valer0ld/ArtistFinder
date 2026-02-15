package com.valer0ld.artistfinder.ui.models

data class UserStatistic(
    var gameStarts: Int,
    var gameEnds: Int,
    var totalTime: Int,
    var totalSteps: Int,
    var bestRating: Int
)

object DanilaStats{
    val stats = UserStatistic(
        gameStarts = 100,
        gameEnds = 98,
        totalTime = 299999,
        totalSteps = 19,
        bestRating = 2
    )
}