package io.ezjay.bikeshare.model

import io.ezjay.bikeshare.Main
import io.realm.Realm
import io.realm.kotlin.where

object BikeshareDao {
    fun readBikes() : List<Bike> {
        val realm = Realm.getInstance(Main.getRealmConfig())
        return realm.where<Bike>().findAll().toList()
    }
    
    fun readRides() : List<Ride> {
        val realm = Realm.getInstance(Main.getRealmConfig())
        return realm.where<Ride>().findAll().toList()
    }
}