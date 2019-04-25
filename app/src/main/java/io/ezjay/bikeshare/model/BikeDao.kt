package io.ezjay.bikeshare.model

import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

object BikeDao {
    fun getBikes() : List<Bike> {
        val realm = Realm.getDefaultInstance()
        return realm.where<Bike>().findAll().toList()
    }

    fun getAvailableBikes() : List<Bike> {
        val realm = Realm.getDefaultInstance()
        return realm.where<Bike>().equalTo("available", true).findAll()
    }

    fun addBike(bike : Bike) : Bike {
        val realm = Realm.getDefaultInstance()
        val index = realm.where<Bike>().count()
        realm.executeTransaction {
            val newBike = it.createObject<Bike>(index)
            newBike.name = bike.name
            newBike.type = bike.type
            newBike.location = bike.location
            newBike.hourlyPrice = bike.hourlyPrice
            newBike.picture = bike.picture
            newBike.available = bike.available
        }
        bike.id = index
        return bike
    }

    fun deleteBike(bike: Bike) {
        val realm = Realm.getDefaultInstance()
        val result = realm.where<Bike>().equalTo("id", bike.id).findAll()
        realm.executeTransaction {
            result.deleteAllFromRealm()
        }
    }

    fun getRealm() : Realm {
        return Realm.getDefaultInstance()
    }
}