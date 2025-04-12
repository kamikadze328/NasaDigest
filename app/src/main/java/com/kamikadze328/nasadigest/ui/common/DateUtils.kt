package com.kamikadze328.nasadigest.ui.common

import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import java.util.Locale

private val dateFormat = DateTimeFormat.forPattern("E dd MMMM yyyy")
private val timeFormat = DateTimeFormat.forPattern("hh:mm")
private val dateTimeFormat = DateTimeFormat.forPattern("dd MMMM")

fun LocalDate.prettyPrint(): String {
    return toString(dateFormat)
}

fun LocalDate.prettyPrintShort(): String {
    return toString(dateTimeFormat)
}

fun DateTime.prettyPrintTime(): String {
    return toString(timeFormat)
}

fun Double.cut(): Double {
    return String.format(Locale.getDefault(), "%.2f", this).toDouble()
}