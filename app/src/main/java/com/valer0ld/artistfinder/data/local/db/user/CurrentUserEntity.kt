package com.valer0ld.artistfinder.data.local.db.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_user")
data class CurrentUserEntity(
    @PrimaryKey val id: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val gameStarts: Int = 0,
    val gameEnds: Int = 0,
    val totalTime: Long = 0,
    val totalSteps: Int = 0,
    val currentRating: Int = 0,
    val bestRating: Int = 0
)