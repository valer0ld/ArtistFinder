package com.valer0ld.artistfinder.data.local.db.game

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.valer0ld.artistfinder.domain.model.Artist

@Entity(tableName = "game_list")
data class GameListEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val start: Artist,
    val target: Artist,
    val duration: Long,
    val stepCounter: Int,
    val steps: List<Artist>,
    val isDaily: Boolean = false,
    val isFinished: Boolean = false
)