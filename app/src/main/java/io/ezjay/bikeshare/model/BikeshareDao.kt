package io.ezjay.bikeshare.model

import io.ezjay.bikeshare.Main
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

object BikeshareDao {
    fun getBikes() : List<Bike> {
        val realm = Realm.getDefaultInstance()
        return realm.where<Bike>().findAll().toList()
    }
    
    fun getRides() : List<Ride> {
        val realm = Realm.getDefaultInstance()
        return realm.where<Ride>().findAll().toList()
    }

    fun addRide(ride : Ride) : Ride {
        val realm = Realm.getDefaultInstance()
        val index = realm.where<Ride>().count()
        realm.executeTransaction {
            val newRide = it.createObject<Ride>(index)
            newRide.bikeName = ride.bikeName
            newRide.startLocation = ride.startLocation
            newRide.endLocation = ride.endLocation
            newRide.startTime = ride.startTime
            newRide.endTime = ride.endTime
            newRide.active = ride.active
        }
        ride.id = index
        return ride
    }

    fun updateRide(ride : Ride) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            val updatedRide = it.where<Ride>().equalTo("id", ride.id).findFirst()
            updatedRide?.bikeName = ride.bikeName
            updatedRide?.startLocation = ride.startLocation
            updatedRide?.endLocation = ride.endLocation
            updatedRide?.startTime = ride.startTime
            updatedRide?.endTime = ride.endTime
            updatedRide?.active = ride.active
        }
    }
}