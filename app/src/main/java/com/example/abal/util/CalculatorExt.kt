package com.example.abal.util

import java.util.Locale

fun Float.formatDistance() : String {
        return String.format(Locale.getDefault(), "%.2f", this)
}
