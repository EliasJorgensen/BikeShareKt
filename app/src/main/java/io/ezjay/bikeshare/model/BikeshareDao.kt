package io.ezjay.bikeshare.model

import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

object BikeshareDao {
    fun getBikes() : List<Bike> {
        val realm = Realm.getDefaultInstance()
        return realm.where<Bike>().findAll().toList()
    }

    fun addBike(bike: Bike) : Bike {
        val realm = Realm.getDefaultInstance()
        val index = realm.where<Bike>().count()
        realm.executeTransaction {
            val newBike = it.createObject<Bike>(index)
            newBike.location = bike.location
            newBike.name = bike.name
        }
        bike.id = index
        return bike
    }
    
    fun getRides() : List<Ride> {
        val realm = Realm.getDefaultInstance()
        return realm.where<Ride>().findAll().toList()
    }

    fun getActiveRide() : Ride? {
        val realm = Realm.getDefaultInstance()
        return realm.where<Ride>().equalTo("active", true).findFirst()
    }

    fun getDoneRides() : List<Ride> {
        val realm = Realm.getDefaultInstance()
        return realm.where<Ride>().equalTo("active", false).findAll()
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

    fun getRealm() : Realm {
        return Realm.getDefaultInstance()
    }
}