package io.ezjay.bikeshare.viewmodel.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.BikeshareDao
import io.ezjay.bikeshare.model.Ride

class EndRideActivity : AppCompatActivity() {

    // UI
    private lateinit var endRide : Button
    private lateinit var header : TextView
    private lateinit var location : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_ride)

        this.endRide = this.findViewById(R.id.end_button)
        this.header = this.findViewById(R.id.header)
        this.location = this.findViewById(R.id.where_text)

        this.endRide.setOnClickListener {
            if (!isEmpty(this.location)) {
                val activeRide = BikeshareDao.getActiveRide()
                BikeshareDao.getRealm().executeTransaction {
                    activeRide?.endLocation = this.location.text.toString().trim()
                    activeRide?.endTime = Ride.getCurrentFormattedDateTime()
                    activeRide?.active = false
                }

                this.updateUI()
            }
        }
    }

    private fun updateUI() {
        this.header.text = "Your ride has been registered."
        this.endRide.isEnabled = false
    }

    private fun isEmpty(text: TextView): Boolean {
        return text.text.toString().isEmpty()
    }
}
