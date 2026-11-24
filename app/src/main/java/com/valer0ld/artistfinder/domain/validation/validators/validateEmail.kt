package com.valer0ld.artistfinder.domain.validation.validators

import com.valer0ld.artistfinder.domain.validation.error_enums.EmailError

fun validateEmail(email: String): EmailError?{
    return when {
        email.isBlank() -> EmailError.EMPTY
        !isValidEmail(email) -> EmailError.INVALID_FORMAT
        else -> null
    }
}

private val EMAIL_REGEX = Regex(
    "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
)

fun isValidEmail(email: String): Boolean{
    return EMAIL_REGEX.matches(email.trim())
}