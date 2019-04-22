package io.ezjay.bikeshare.viewmodel.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.util.GpsManager

class StartRideActivity : AppCompatActivity() {

    // UI
    private lateinit var dropdown : Spinner
    private lateinit var startLocation : TextView

    private lateinit var startRide : Button

    private lateinit var gpsManager: GpsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_ride)

        this.dropdown = this.findViewById(R.id.bike_dropdown)
        this.startLocation = this.findViewById(R.id.ride_list)
        this.startRide = this.findViewById(R.id.start_ride)

        this.startRide.setOnClickListener {
            this.startRide()
        }

        this.gpsManager.requestLocationUpdates()
        this.startLocation.text = GpsManager.locationToString(this.gpsManager.currentLocation)
    }

    private fun startRide() {

    }
}
