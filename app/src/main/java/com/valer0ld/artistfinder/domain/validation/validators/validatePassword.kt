package com.valer0ld.artistfinder.domain.validation.validators

import com.valer0ld.artistfinder.domain.validation.error_enums.PasswordError

fun validatePassword(password: String): PasswordError? {
    return when {
        password.isBlank() -> PasswordError.EMPTY
        password.length < 8 -> PasswordError.TOO_SHORT
        else -> null
    }
}