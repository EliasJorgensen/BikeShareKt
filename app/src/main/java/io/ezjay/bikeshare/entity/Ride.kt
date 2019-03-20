package io.ezjay.bikeshare.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.text.format.DateFormat
import java.util.*

@Entity(tableName = "rides")
data class Ride(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "bike_name")  var bikeName: String,
    @ColumnInfo(name = "start_ride") var startRide: String,
    @ColumnInfo(name = "end_ride")   var endRide: String,
    @ColumnInfo(name = "start_time") var startTime: Date,
    @ColumnInfo(name = "end_time")   var endTime: Date
) {
    override fun toString(): String {
        return if (this.bikeName.isBlank() && this.startRide.isBlank())
            "You have no last ride"
            else "${this.bikeName} started here ${this.startRide}, at ${formatDate(
            startTime
        )}, and ended here ${this.endRide}, at ${formatDate(endTime)}"
    }

    companion object {
        fun formatDate(date: Date): String {
            return DateFormat.format("MMM d, yyyy", date) as String
        }
    }
}