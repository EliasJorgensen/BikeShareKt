package io.ezjay.bikeshare.model

import io.realm.Realm
import io.realm.SyncCredentials
import io.realm.kotlin.createObject
import io.realm.kotlin.where

object RideDao {
    fun getRide(id: Long) : Ride? {
        val realm = Realm.getDefaultInstance()
        return realm.where<Ride>().equalTo("id", id).findFirst()
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
            newRide.startLocation = ride.startLocation
            newRide.endLocation = ride.endLocation
            newRide.startTime = ride.startTime
            newRide.endTime = ride.endTime
            newRide.bike = ride.bike
            newRide.totalCost = ride.totalCost
            newRide.active = ride.active
        }
        ride.id = index
        return ride
    }

    fun deleteRide(ride: Ride) {
        val realm = Realm.getDefaultInstance()
        val result = realm.where<Ride>().equalTo("id", ride.id).findAll()
        realm.executeTransaction {
            result.deleteAllFromRealm()
        }
    }

    fun getRealm() : Realm {
        return Realm.getDefaultInstance()
    }
}