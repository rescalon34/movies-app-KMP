package com.escalondev.movies_app_kmp.android.ui.filter

enum class SortType(val displayName: String, val sortType: String) {
    FIRST_ADDED("First Added", "created_at.asc"),
    LAST_ADDED("Last Added", "created_at.desc")
}
