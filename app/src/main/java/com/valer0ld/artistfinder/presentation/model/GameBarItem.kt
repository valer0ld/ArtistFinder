package com.valer0ld.artistfinder.presentation.model

import com.valer0ld.artistfinder.R


data class GameBarItem(
    val title: String,
    val image: Int
)

object CounterIconItems{
    val IconItems = listOf(
        GameBarItem(
            title = "counter 0",
            image = R.drawable.counter_0_24px
        ),
        GameBarItem(
            title = "counter 1",
            image = R.drawable.counter_1_24px
        ),
        GameBarItem(
            title = "counter 2",
            image = R.drawable.counter_2_24px
        ),
        GameBarItem(
            title = "counter 3",
            image = R.drawable.counter_3_24px
        )
    )
}