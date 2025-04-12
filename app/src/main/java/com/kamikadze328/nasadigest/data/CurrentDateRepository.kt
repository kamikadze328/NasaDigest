package com.kamikadze328.nasadigest.data

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import javax.inject.Inject

class CurrentDateRepository @Inject constructor() {
    fun today(): Long {
        return DateTime(DateTimeZone.UTC).withTimeAtStartOfDay().millis
    }

    fun makeStartOfDay(date: Long): Long {
        return DateTime(date, DateTimeZone.UTC).withTimeAtStartOfDay().millis
    }
}