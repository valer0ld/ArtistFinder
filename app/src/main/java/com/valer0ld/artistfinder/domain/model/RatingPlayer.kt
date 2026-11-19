package com.valer0ld.artistfinder.domain.model

data class RatingPlayer(
    val id: Long,
    val username: String,
    var pictureUrl: String = "",
    var dailyGames: Int = 0,
    var dailySteps: Int = 0,
    var dailyTime: Long = 0,
    val sumScore: Int = 0,
    var place: Int = 0
)