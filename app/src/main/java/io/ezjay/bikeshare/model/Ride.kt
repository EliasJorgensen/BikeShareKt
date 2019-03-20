package io.ezjay.bikeshare.model

import android.text.format.DateFormat
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.io.Serializable
import java.util.*

@RealmClass
open class Ride (
    @PrimaryKey var id: Long = 0,
    var bikeName: String,
    var startRide: String,
    var endRide: String,
    var startTime: String,
    var endTime: String,
    var active: Boolean
) : RealmObject(), Serializable {
    override fun toString(): String {
        return if (this.bikeName.isBlank() && this.startRide.isBlank())
            "You have no last ride"
            else "${this.bikeName} started here ${this.startRide}, at " +
                "${this.startTime}, and ended here ${this.endRide}, at ${this.endTime}"
    }

    companion object {
        fun getFormattedDate(): String {
            return DateFormat.format("MMM d, yyyy", Date()) as String
        }
    }
}