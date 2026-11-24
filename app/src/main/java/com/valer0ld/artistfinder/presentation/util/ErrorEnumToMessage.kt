package com.valer0ld.artistfinder.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.domain.validation.error_enums.ArtistSearchError
import com.valer0ld.artistfinder.domain.validation.error_enums.ConfirmPasswordError
import com.valer0ld.artistfinder.domain.validation.error_enums.EmailError
import com.valer0ld.artistfinder.domain.validation.error_enums.ErrorEnums
import com.valer0ld.artistfinder.domain.validation.error_enums.PasswordError

@Composable
fun ErrorEnums.toMessage(): String {
    return when (this) {
        EmailError.EMPTY,
        PasswordError.EMPTY,
        ConfirmPasswordError.EMPTY,
        ArtistSearchError.EMPTY ->
            stringResource(R.string.empty_error)

        EmailError.INVALID_FORMAT ->
            stringResource(R.string.invalid_format_error)

        EmailError.NOT_EXIST ->
            stringResource(R.string.not_exist_error)

        PasswordError.INCORRECT ->
            stringResource(R.string.incorrect_data_error)

        PasswordError.TOO_SHORT ->
            stringResource(R.string.too_short_error)

        ConfirmPasswordError.MISMATCH ->
            stringResource(R.string.mismatch_error)

        ArtistSearchError.NOT_FOUND ->
            stringResource(R.string.not_found_error)

        else ->
            stringResource(R.string.unknown_error)
    }
}