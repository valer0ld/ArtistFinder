package com.valer0ld.artistfinder.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.valer0ld.artistfinder.data.local.converters.Converters
import com.valer0ld.artistfinder.data.local.db.game.GameListDao
import com.valer0ld.artistfinder.data.local.db.game.GameListEntity
import com.valer0ld.artistfinder.data.local.db.current_user.CurrentUserDao
import com.valer0ld.artistfinder.data.local.db.current_user.CurrentUserEntity
import com.valer0ld.artistfinder.data.local.db.public_user.PublicUserDao
import com.valer0ld.artistfinder.data.local.db.public_user.PublicUserEntity

@Database(
    entities = [CurrentUserEntity::class, GameListEntity::class, PublicUserEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CurrentUserDao(): CurrentUserDao

    abstract fun GameListDao() : GameListDao

    abstract fun PublicUserDao() : PublicUserDao
}