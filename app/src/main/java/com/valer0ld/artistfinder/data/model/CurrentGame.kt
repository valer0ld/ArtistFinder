package com.valer0ld.artistfinder.data.model

import com.valer0ld.artistfinder.domain.model.Artist

data class CurrentGame(
    val currentArtist: Artist,
    val targetArtist: Artist,
    val steps: List<Artist>,
    val currentStep: Int = 0,
    val maxReturns: Int = 3,
    val currentReturns: Int = maxReturns,
    val startTime: Long = 0,
    val duration: Long = 0,
    val isDaily: Boolean = false,
    val isFinished: Boolean = false
)