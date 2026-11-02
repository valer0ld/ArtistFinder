package com.valer0ld.artistfinder.domain.model

import com.valer0ld.artistfinder.R

data class RatingPlayer(
    val id: Int,
    val name: String,
    var picture: Int = R.drawable.webp_user_icon_black,
    var dailyGames: Int,
    var totalSteps: Int,
    var totalTime: String,
    val sumScore: Int,
    var place: Int
)

object TemporaryPlayerList{
    val players = listOf(
        RatingPlayer(
            id = 1,
            name = "Третий",

            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place = 3
        ),
        RatingPlayer(
            id = 2,
            name = "Danil Kolbasenko",
            dailyGames = 14,
            totalSteps = 111,
            totalTime = "00:01:12",
            sumScore = 104,
            place = 2
        ),
        RatingPlayer(
            id =3,
            name = "Первый",
            dailyGames = 44,
            totalSteps = 1234,
            totalTime = "00:34:11",
            sumScore = 132400,
            place = 1
        ),
        RatingPlayer(
            id = 4,
            name = "Valera1",
            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place =4
        ),
        RatingPlayer(
            id = 5,
            name = "Valera1",
            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place =4
        ),
        RatingPlayer(
            id = 6,
            name = "Valera1",
            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place =4
        ),
        RatingPlayer(
            id = 7,
            name = "Valera1",
            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place =4
        ),
        RatingPlayer(
            id = 8,
            name = "Valera1",
            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place =4
        ),
        RatingPlayer(
            id = 9,
            name = "Valera1",
            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place =4
        ),
        RatingPlayer(
            id = 10,
            name = "Valera1",
            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place =4
        ),
        RatingPlayer(
            id = 11,
            name = "Valera1",
            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place =4
        ),
        RatingPlayer(
            id = 12,
            name = "Valera1",
            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place =4
        ),
        RatingPlayer(
            id = 13,
            name = "Valera1",
            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place =4
        ),
        RatingPlayer(
            id = 14,
            name = "Valera1",
            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place =4
        ),
        RatingPlayer(
            id = 15,
            name = "Valera1",
            dailyGames = 12,
            totalSteps = 120,
            totalTime = "00:00:11",
            sumScore = 1000,
            place =4
        ),
    )

    val currentPlayer = RatingPlayer(
        id = 2,
        name = "Danil Kolbasenko",
        dailyGames = 14,
        totalSteps = 111,
        totalTime = "00:01:12",
        sumScore = 104,
        place = 2
    )
}