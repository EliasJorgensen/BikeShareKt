package io.ezjay.bikeshare.viewmodel.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.BikeshareDao
import io.ezjay.bikeshare.model.Ride

class StartRideActivity : AppCompatActivity() {

    // UI
    private lateinit var addRide : Button
    private lateinit var header : TextView
    private lateinit var bikeName : TextView
    private lateinit var location : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_ride)

        this.addRide = this.findViewById(R.id.add_button)
        this.header = this.findViewById(R.id.header)
        this.bikeName = this.findViewById(R.id.what_text)
        this.location = this.findViewById(R.id.where_text)

        this.addRide.setOnClickListener {
            if (!isEmpty(this.bikeName) && !isEmpty(this.location)) {
                val ride = Ride(
                    bikeName = this.bikeName.text.toString(),
                    startLocation = this.location.text.toString()
                )

                BikeshareDao.addRide(ride)

                this.updateUI()
            }
        }

    }

    private fun updateUI() {
        this.header.text = "Ride started, go get 'em!"
    }

    private fun isEmpty(text: TextView): Boolean {
        return text.text.toString().isEmpty()
    }
}
