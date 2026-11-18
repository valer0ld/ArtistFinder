package com.valer0ld.artistfinder.domain.model


data class CurrentUser (
    val id: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val gameStarts: Int = 0,
    val gameEnds: Int = 0,
    val totalTime: Long = 0,
    val totalSteps: Int = 0,
    val dailyGameStarts: Int = 0,
    val dailyGameEnds: Int = 0,
    val dailyTime: Long = 0,
    val dailySteps: Int = 0,
    val currentRating: Int = 0,
    val bestRating: Int = 0
)