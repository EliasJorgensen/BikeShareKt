package io.ezjay.bikeshare.model

import android.text.format.DateFormat
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.io.Serializable
import java.util.*

@RealmClass
open class Ride (
    @PrimaryKey var id: Long = 0,
    var bikeName: String = "",
    var startLocation: String = "",
    var endLocation: String = "",
    var startTime: String = "",
    var endTime: String = "",
    var active: Boolean = false,
    var bikePicture: ByteArray = ByteArray(0)
) : RealmModel {
    override fun toString(): String {
        return if (this.bikeName.isBlank() && this.startLocation.isBlank())
            "You have no last ride"
            else "${this.bikeName} started here ${this.startLocation}, at " +
                "${this.startTime}, and ended here ${this.endLocation}, at ${this.endTime}"
    }

    companion object {
        fun getCurrentFormattedDateTime(): String {
            return DateFormat.format("MMM d, yyyy", Date()) as String
        }
    }
}