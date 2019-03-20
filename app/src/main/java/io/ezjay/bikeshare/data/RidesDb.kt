package io.ezjay.bikeshare.data

import io.ezjay.bikeshare.entity.Ride
import java.util.*

object RidesDb {
    private val allRides : MutableList<Ride> = mutableListOf()

    fun getRides() : List<Ride> {
        return this.allRides
    }

    fun addRide(what : String, where : String) {
        this.allRides.add(Ride(null, what, where, "", Date(), Date()))
    }

    fun endRide(what: String, where : String) {
        this.allRides.add(Ride(null, what, "", where, Date(), Date()))
    }
}