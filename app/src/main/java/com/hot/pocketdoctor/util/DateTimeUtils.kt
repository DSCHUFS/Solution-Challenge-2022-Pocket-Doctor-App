package com.hot.pocketdoctor.util

object DateTimeUtils {

    fun convertWeeklyToDailyTime(weeklyTime: String): Map<String, String> {
        val splitWithDay = weeklyTime.split(",")
        return splitWithDay.map { it.split("-")[0] to it.split("-")[1] }.toMap()
    }
}