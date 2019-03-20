package io.ezjay.bikeshare.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.ezjay.bikeshare.entity.Ride

@Dao
interface RideDao {
    @Query("SELECT * FROM rides")
    fun getAll(): List<Ride>

    @Insert
    fun add()
}