package com.valer0ld.artistfinder.domain.model

 data class Game (
     val id: Int,
    val start: String,
    val target: String,
    val time: Int,
    val stepCounter: Int,
    val steps: List<Artist>
)


object TempListOfGames{
    val list = listOf(
        Game(
            id = 1,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0)
            )
        ),
        Game(
            id = 2,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0)
            )
        ),
        Game(
            id = 3,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0)
            )
        ),
        Game(
            id = 4,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0)
            )
        ),
        Game(
            id = 5,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0)
            )
        ),
        Game(
            id = 6,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0)
            )
        ),

        Game(
            id = 7,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0)
            )
        ),
        Game(
            id = 8,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0)
            )
        ),
        Game(
            id = 9,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0),
                Artist("Воскресенье", 0)
            )
        ),
    )
}