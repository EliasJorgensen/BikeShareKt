package io.ezjay.bikeshare.data

object RidesDb {
    private val allRides : MutableList<Ride> = mutableListOf()

    fun getRides() : List<Ride> {
        return this.allRides
    }

    fun addRide(what : String, where : String) {
        this.allRides.add(Ride(what, where, ""))
    }

    fun endRide(what: String, where : String) {
        this.allRides.add(Ride(what, "", where))
    }
}