package com.valer0ld.artistfinder.domain.validation.validators

import com.valer0ld.artistfinder.domain.validation.error_enums.ConfirmPasswordError

fun validateConfirmPassword(password: String, confirm: String): ConfirmPasswordError? {
    return when {
        confirm.isBlank() -> ConfirmPasswordError.EMPTY
        confirm.trim() != password.trim() -> ConfirmPasswordError.MISMATCH
        else -> null
    }
}