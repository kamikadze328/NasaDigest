package com.kamikadze328.nasadigest.ui.common

import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class DateParser @Inject constructor() {
    companion object {
        private val FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd")
        private val FORMAT_DATE_TIME = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm'Z'")
    }

    fun parse(date: String?): LocalDate? {
        return runCatching { LocalDate.parse(date, FORMAT) }.getOrNull()
    }

    fun parseToPrettyPrint(date: String?): String? {
        return parse(date)?.prettyPrint()
    }

    fun parseToPrettyPrintShort(date: String?): String? {
        return parseFull(date)?.prettyPrintShort()
    }

    fun parseToPrettyPrintTime(date: String?): String? {
        return runCatching { DateTime.parse(date)?.prettyPrintTime() }.getOrNull()
    }

    fun parseFull(date: String?): LocalDate? {
        return runCatching { LocalDate.parse(date, FORMAT_DATE_TIME) }.getOrNull()
    }
}