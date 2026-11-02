package com.valer0ld.artistfinder.domain.model

 data class Game (
     val id: Int,
    val start: String,
    val target: String,
    val time: Int,
    val stepCounter: Int,
    val steps: List<String>
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
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
        Game(
            id = 2,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
        Game(
            id = 3,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
        Game(
            id = 4,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
        Game(
            id = 5,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
        Game(
            id = 6,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
        Game(
            id = 7,
            start = "Воскресенье",
            target = "Земфира",
            time = 150,
            stepCounter = 20,
            steps = listOf(
                "Воскресенье",
                "Земфира",
                "ДДТ",
                "СВ",
                "Аквариум",
                "Машина времени",
                "Чайф"
            )
        ),
    )
}