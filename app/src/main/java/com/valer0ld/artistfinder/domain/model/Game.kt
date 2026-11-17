package com.valer0ld.artistfinder.domain.model

 data class Game (
     val id: Long,
     val start: Artist,
     val target: Artist,
     val duration: Long,
     val stepCounter: Int,
     val steps: List<Artist>,
     val isDaily: Boolean = false,
     val isFinished: Boolean = false
)