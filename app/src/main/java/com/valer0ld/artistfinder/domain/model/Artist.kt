package com.valer0ld.artistfinder.domain.model

import com.valer0ld.artistfinder.R

data class Artist(
    val name: String,
    val image: Int
)

object TempArtist{
    val list = listOf(
        Artist(
            name = "Воскресенье",
            image = R.drawable.voskresenje
        ),
        Artist(
            name = "Земфира",
            image = R.drawable.zemfira
        ),
        Artist(
            name = "Аквариум",
            image = R.drawable.akvarium
        ),
        Artist(
            name = "ДДТ",
            image = R.drawable.ddt
        ),
        Artist(
            name = "ЧИЖ",
            image = R.drawable.chiz
        )
    )
}
