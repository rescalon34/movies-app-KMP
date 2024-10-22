package com.escalondev.movies_app_kmp.android.util

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDate(): String? {
    val inputFormat = SimpleDateFormat(YEAR_MONTH_DAY_FORMAT, Locale.getDefault())
    val outputFormat = SimpleDateFormat(MONTH_DAY_YEAR_FORMAT, Locale.getDefault())
    return inputFormat.parse(this)?.let { outputFormat.format(it) }
}

const val YEAR_MONTH_DAY_FORMAT = "yyyy-MM-dd"
const val MONTH_DAY_YEAR_FORMAT = "MMMM dd, yyyy"
