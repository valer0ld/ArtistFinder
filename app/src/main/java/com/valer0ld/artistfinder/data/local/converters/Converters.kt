package com.valer0ld.artistfinder.data.local.converters

import androidx.room.TypeConverter
import com.valer0ld.artistfinder.domain.model.Artist
import kotlinx.serialization.json.Json

class Converters {

    private val json = Json

    @TypeConverter
    fun fromArtist(value: Artist): String = json.encodeToString(value)

    @TypeConverter
    fun toArtist(value: String): Artist = json.decodeFromString(value)

    @TypeConverter
    fun fromArtistList(value: List<Artist>): String = json.encodeToString(value)

    @TypeConverter
    fun toArtistList(value: String): List<Artist> = json.decodeFromString(value)

}