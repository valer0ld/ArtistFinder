package com.valer0ld.artistfinder.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.valer0ld.artistfinder.data.local.converters.Converters
import com.valer0ld.artistfinder.data.local.db.game.GameListDao
import com.valer0ld.artistfinder.data.local.db.game.GameListEntity
import com.valer0ld.artistfinder.data.local.db.user.CurrentUserDao
import com.valer0ld.artistfinder.data.local.db.user.CurrentUserEntity

@Database(
    entities = [CurrentUserEntity::class, GameListEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CurrentUserDao(): CurrentUserDao

    abstract fun GameListDao() : GameListDao
}