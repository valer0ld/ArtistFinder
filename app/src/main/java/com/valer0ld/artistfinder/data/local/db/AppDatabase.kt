package com.valer0ld.artistfinder.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.valer0ld.artistfinder.data.local.db.user.CurrentUserDao
import com.valer0ld.artistfinder.data.local.db.user.CurrentUserEntity

@Database(
    entities = [CurrentUserEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun CurrentUserDao(): CurrentUserDao
}