package com.luizalabs.registration.presentation.common

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Long.toDateString(): String {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@toDateString
    }
    val format = SimpleDateFormat("yyyy/MMM/dd", Locale.getDefault())
    return format.format(calendar.time)
}

fun String.dateToLong(): Long {
    val format = SimpleDateFormat("yyyy/MMM/dd", Locale.getDefault())
    return format.parse(this)?.time ?: throw IllegalArgumentException("Invalid date")
}
