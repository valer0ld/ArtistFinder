package com.valer0ld.artistfinder.domain.validation.validators

import com.valer0ld.artistfinder.domain.model.TempArtist
import com.valer0ld.artistfinder.domain.validation.error_enums.ArtistSearchError

fun validateArtist(artist: String) : ArtistSearchError? {
    return when {
        artist.isBlank() -> ArtistSearchError.EMPTY
        TempArtist.list.none {
            it.name == artist
        } -> ArtistSearchError.NOT_FOUND // потом брать информацию из сервера
        else -> null
    }
}