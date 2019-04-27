package io.ezjay.bikeshare.util

import android.text.format.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.absoluteValue

object DateUtils {
    fun toHoursAndMinutes(millis : Long) : String {
        val hrs = TimeUnit.MILLISECONDS.toHours(millis).toInt().absoluteValue % 24
        val min = TimeUnit.MILLISECONDS.toMinutes(millis).toInt().absoluteValue % 60
        return String.format("%02d:%02d", hrs, min)
    }

    fun getDifference(from: Date, to: Date) : Long {
        return to.time - from.time
    }

    fun format(date: Date) : String {
        return DateFormat.format("MMM d, yyyy", date) as String
    }
}