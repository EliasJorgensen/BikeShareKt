package io.ezjay.bikeshare.viewmodel.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.BikeDao
import io.ezjay.bikeshare.model.Ride
import io.ezjay.bikeshare.model.RideDao
import io.ezjay.bikeshare.util.GpsManager
import java.util.*

class StartRideActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    // UI
    private lateinit var dropdown : Spinner
    private lateinit var dropdownDescription : TextView
    private lateinit var startLocation : TextView
    private lateinit var startRide : Button
    private lateinit var addedText : TextView

    private lateinit var gpsManager: GpsManager
    private val bikeList = BikeDao.getAvailableBikes()

    private var selectedBike = 0
    private var hasStartedRide = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_ride)

        this.dropdown = this.findViewById(R.id.bike_dropdown)
        this.dropdownDescription = this.findViewById(R.id.dropdown_description)
        this.startLocation = this.findViewById(R.id.ride_location)
        this.startRide = this.findViewById(R.id.add_button)
        this.addedText = this.findViewById(R.id.added_text)

        this.gpsManager = GpsManager(this)

        this.startRide.setOnClickListener {
            this.startRide()
        }

        this.gpsManager.requestLocationUpdates()
        this.startLocation.text = GpsManager.locationToString(this.gpsManager.currentLocation)

        if (bikeList.isNotEmpty()) {
            ArrayAdapter(
                this,
                R.layout.dropdown_item_bike,
                R.id.bike_name,
                bikeList.map { it.name }).also {
                dropdown.adapter = it
                dropdown.onItemSelectedListener = this
            }
        } else {
            this.dropdown.isEnabled = false
            this.startRide.isEnabled = false
            this.dropdownDescription.visibility = View.VISIBLE
        }

        if (savedInstanceState != null) {
            this.selectedBike = savedInstanceState.getInt("selectedBike")
            this.hasStartedRide = savedInstanceState.getBoolean("hasStartedRide")

            this.startRide.isEnabled = !this.hasStartedRide
            this.addedText.visibility = if (this.hasStartedRide) View.VISIBLE else View.GONE
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        this.selectedBike = 0
    }

    private fun startRide() {
        val bike = this.bikeList[this.selectedBike]

        RideDao.addRide(Ride(
            bike = bike,
            startLocation = GpsManager.locationToString(this.gpsManager.currentLocation),
            startTime = Date(),
            active = true
        ))

        BikeDao.getRealm().executeTransaction {
            bike.available = false
        }

        this.startRide.isEnabled = false
        this.addedText.visibility = View.VISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt("selectedBike", this.selectedBike)
        outState?.putBoolean("hasStartedRide", this.hasStartedRide)
        super.onSaveInstanceState(outState)
    }
}
