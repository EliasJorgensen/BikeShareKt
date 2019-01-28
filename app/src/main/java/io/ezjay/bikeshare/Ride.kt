package io.ezjay.bikeshare

data class Ride(var bikeName: String, var startRide: String) {
    override fun toString(): String {
        return if (this.bikeName.isBlank() && this.startRide.isBlank())
            "You have no last ride"
            else "${this.bikeName} started here ${this.startRide}"
    }
}