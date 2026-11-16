package com.valer0ld.artistfinder.domain.model

 data class Game (
     val id: Int,
     val start: Artist,
     val target: Artist,
     val time: Int,
     val stepCounter: Int,
     val steps: List<Artist>,
     val isDaily: Boolean = false
)


object TempListOfGames{
    val list = listOf(
        Game(
            id = 1,
            start = TempArtist.list[0],
            target = TempArtist.list[1],
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
            start = TempArtist.list[0],
            target = TempArtist.list[1],
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
            start = TempArtist.list[0],
            target = TempArtist.list[1],
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
            start = TempArtist.list[0],
            target = TempArtist.list[1],
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
            start = TempArtist.list[0],
            target = TempArtist.list[1],
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
            start = TempArtist.list[0],
            target = TempArtist.list[1],
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
            start = TempArtist.list[0],
            target = TempArtist.list[1],
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
            start = TempArtist.list[0],
            target = TempArtist.list[1],
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
            start = TempArtist.list[0],
            target = TempArtist.list[1],
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