package io.ezjay.bikeshare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import io.ezjay.bikeshare.adapter.RideArrayAdapter
import io.ezjay.bikeshare.data.RidesDb

class BikeShareActivity : AppCompatActivity() {

    // UI
    private lateinit var addRide : Button
    private lateinit var endRide : Button
    private lateinit var list : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_share)

        this.addRide = this.findViewById(R.id.add_button)
        this.endRide = this.findViewById(R.id.end_button)

        this.addRide.setOnClickListener {
            val intent = Intent(this,  StartRideActivity::class.java)
            this.startActivity(intent)
        }

        this.endRide.setOnClickListener {
            val intent = Intent(this, EndRideActivity::class.java)
            this.startActivity(intent)
        }

        this.list = this.findViewById(R.id.ride_list)
        this.list.adapter = RideArrayAdapter(this, RidesDb.getRides())
    }

    override fun onStart() {
        super.onStart()
        this.list.adapter = RideArrayAdapter(this, RidesDb.getRides())
    }
}
