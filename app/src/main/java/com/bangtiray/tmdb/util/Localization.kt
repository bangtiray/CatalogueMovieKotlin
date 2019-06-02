package com.bangtiray.tmdb.util

import java.util.*


object Localization {

    val country: String
        get() {
            var country = Locale.getDefault().country.toLowerCase()

            when (country) {
                "id" -> {
                }
                else -> country = "en"
            }

            return country
        }
}