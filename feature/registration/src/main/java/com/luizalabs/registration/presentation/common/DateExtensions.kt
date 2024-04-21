package com.luizalabs.registration.presentation.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDateString(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy/MMM/dd", Locale.getDefault())
    return format.format(date)
}

fun String.dateToLong(): Long {
    val format = SimpleDateFormat("yyyy/MMM/dd", Locale.getDefault())
    return format.parse(this)?.time ?: throw IllegalArgumentException("Invalid date")
}
