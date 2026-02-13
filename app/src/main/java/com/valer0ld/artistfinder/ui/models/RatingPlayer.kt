package com.valer0ld.artistfinder.ui.models

data class RatingPlayer(
    val id: Int,
    val name: String,
    var dailyGames: Int,
    var totalSteps: Int,
    var totalTime: String,
    val sumScore: Int,
    var place: Int
)

object TemporaryPlayerList{
    val players = listOf(
        RatingPlayer(
            1,
            "Третий",
            12,
            120,
            "00:00:11",
            1000,
            3
        ),
        RatingPlayer(
            2,
            "Danil Kolbasenko",
            14,
            111,
            "00:01:12",
            104,
            2
        ),
        RatingPlayer(
            3,
            "Первый",
            44,
            1234,
            "00:34:11",
            132400,
            1
        ),
        RatingPlayer(
            4,
            "Valera1",
            12,
            120,
            "00:00:11",
            1000,
            4
        ),
        RatingPlayer(
            5,
            "Valera1",
            12,
            120,
            "00:00:11",
            1000,
            4
        ),
        RatingPlayer(
            6,
            "Valera1",
            12,
            120,
            "00:00:11",
            1000,
            4
        ),
        RatingPlayer(
            7,
            "Valera1",
            12,
            120,
            "00:00:11",
            1000,
            4
        ),
        RatingPlayer(
            8,
            "Valera1",
            12,
            120,
            "00:00:11",
            1000,
            4
        ),
    )

    val currentPlayer = RatingPlayer(
        2,
        "Danil Kolbasenko",
        14,
        111,
        "00:01:12",
        104,
        2
    )
}