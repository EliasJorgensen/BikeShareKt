package io.ezjay.bikeshare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import io.ezjay.bikeshare.data.Ride

class BikeShareActivity : AppCompatActivity() {

    // UI
    private lateinit var addRide : Button
    private lateinit var endRide : Button

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
    }
}
