package io.ezjay.bikeshare.data

import android.text.format.DateFormat
import java.util.*

data class Ride(
    var bikeName: String,
    var startRide: String,
    var endRide: String,
    var startTime: Date,
    var endTime: Date
) {
    override fun toString(): String {
        return if (this.bikeName.isBlank() && this.startRide.isBlank())
            "You have no last ride"
            else "${this.bikeName} started here ${this.startRide}, at ${formatDate(startTime)}, and ended here ${this.endRide}, at ${formatDate(endTime)}"
    }

    companion object {
        fun formatDate(date: Date): String {
            return DateFormat.format("MMM d, yyyy", date) as String
        }
    }
}