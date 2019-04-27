package io.ezjay.bikeshare.viewmodel.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.Ride
import io.ezjay.bikeshare.model.RideDao
import io.ezjay.bikeshare.util.DateUtils
import io.ezjay.bikeshare.util.GpsManager
import io.ezjay.bikeshare.util.Pricing
import java.util.*

class EndRideActivity : AppCompatActivity() {

    // UI
    private lateinit var header : TextView
    private lateinit var locationText : TextView
    private lateinit var durationText : TextView
    private lateinit var totalCostText : TextView
    private lateinit var endRide : Button

    private lateinit var gpsManager: GpsManager
    private lateinit var activeRide : Ride
    private val newDate = Date()

    private var rideEnded = false
    private val rideEndedText = "Ride ended"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_ride)

        this.header = this.findViewById(R.id.header)
        this.locationText = this.findViewById(R.id.where_text)
        this.durationText = this.findViewById(R.id.duration_text)
        this.totalCostText = this.findViewById(R.id.total_cost)
        this.endRide = this.findViewById(R.id.end_button)

        if (savedInstanceState != null) {
            this.activeRide = RideDao.getRide(savedInstanceState.getLong("activeRide"))!!
            this.rideEnded = savedInstanceState.getBoolean("rideEnded")
            if (this.rideEnded) {
                this.header.text = this.rideEndedText
                this.endRide.isEnabled = !this.rideEnded
            }
        } else {
            this.activeRide = RideDao.getActiveRide()!!
        }

        this.gpsManager = GpsManager(this)
        this.gpsManager.requestLocationUpdates()
        this.locationText.text = GpsManager.locationToString(this.gpsManager.currentLocation)

        val duration = DateUtils.getDifference(this.newDate, this.activeRide.startTime!!)
        this.durationText.text = DateUtils.toHoursAndMinutes(duration)

        this.totalCostText.text = Pricing.getPriceFromUsage(
            this.activeRide.startTime!!,
            this.newDate,
            this.activeRide.bike!!.hourlyPrice!!
        ).toString()

        this.endRide.setOnClickListener {
            this.submit()
        }
    }

    private fun submit() {
        RideDao.getRealm().executeTransaction {
            val dateTime = Date()
            this.activeRide.endTime = dateTime
            this.activeRide.totalCost = Pricing.getPriceFromUsage(
                this.activeRide.startTime!!,
                dateTime,
                this.activeRide.bike!!.hourlyPrice!!
            )
            this.activeRide.endLocation = GpsManager.locationToString(this.gpsManager.currentLocation)
            this.activeRide.active = false
            this.activeRide.bike?.available = true
        }

        this.rideEnded = true
        this.header.text = this.rideEndedText
        this.endRide.isEnabled = !this.rideEnded
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean("rideEnded", this.rideEnded)
        outState?.putLong("activeRide", this.activeRide.id!!)
        super.onSaveInstanceState(outState)
    }
}
