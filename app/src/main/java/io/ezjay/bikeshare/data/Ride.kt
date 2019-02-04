package io.ezjay.bikeshare.data

data class Ride(var bikeName: String, var startRide: String, var endRide: String) {
    override fun toString(): String {
        return if (this.bikeName.isBlank() && this.startRide.isBlank())
            "You have no last ride"
            else "${this.bikeName} started here ${this.startRide} and ended here ${this.endRide}"
    }
}