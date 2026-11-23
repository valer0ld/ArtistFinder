package com.valer0ld.artistfinder.presentation.util

import android.app.LocaleManager
import android.content.Context
import android.os.LocaleList
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.valer0ld.artistfinder.domain.model.AppLanguage

fun Context.applyLanguage(language: AppLanguage) {
    val localeManager = getSystemService(LocaleManager::class.java)

    localeManager.applicationLocales = when (language) {
        AppLanguage.SYSTEM -> LocaleList.getEmptyLocaleList()
        AppLanguage.RUSSIAN -> LocaleList.forLanguageTags("ru")
        AppLanguage.ENGLISH -> LocaleList.forLanguageTags("en")
    }
}