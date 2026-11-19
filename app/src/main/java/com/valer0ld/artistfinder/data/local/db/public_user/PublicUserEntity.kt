package com.valer0ld.artistfinder.data.local.db.public_user

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "public_user")
data class PublicUserEntity(
    @PrimaryKey val id: Long,
    val username: String,
    var pictureUrl: String = "",
    var dailyGames: Int = 0,
    var dailySteps: Int = 0,
    var dailyTime: Long = 0,
    val sumScore: Int = 0,
    var place: Int = 0
)