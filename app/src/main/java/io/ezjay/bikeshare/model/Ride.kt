package io.ezjay.bikeshare.model

import android.text.format.DateFormat
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class Ride (
    @PrimaryKey var id: Long? = null,
    var startLocation: String? = null,
    var endLocation: String? = null,
    var startTime: Date? = null,
    var endTime: Date? = null,
    var bike: Bike? = null,
    var totalCost: Float? = null,
    var active: Boolean = true
) : RealmModel {
    override fun toString(): String {
        return if (this.bike?.name!!.isBlank() && this.startLocation!!.isBlank())
            "You have no last ride"
            else "${this.bike?.name} started here ${this.startLocation}, at " +
                "${this.startTime}, and ended here ${this.endLocation}, at ${this.endTime}"
    }

    fun getFormattedStartTime(): String? {
        return this.startTime?.let { formatDate(it) }
    }

    fun getFormattedEndTime(): String? {
        return this.endTime?.let { formatDate(it) }
    }

    companion object {
        private fun formatDate(date : Date) : String {
            return DateFormat.format("MMM d, yyyy", date) as String
        }
    }
}