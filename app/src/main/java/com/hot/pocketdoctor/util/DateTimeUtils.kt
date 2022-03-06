package com.hot.pocketdoctor.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    fun convertWeeklyToDailyTime(weeklyTime: String): Map<String, String> {
        val splitWithDay = weeklyTime.split(",")
        return splitWithDay.map { it.split("-")[0] to it.split("-")[1] }.toMap()
    }

    fun convertMillisToDateTime(dateInMillis: Long): String {
        val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        return dateTimeFormat.format(Date(dateInMillis))
    }
}