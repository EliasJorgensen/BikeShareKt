package io.ezjay.bikeshare.viewmodel.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.Ride

class EndRideActivity : AppCompatActivity() {

    // UI
    private lateinit var endRide : Button
    private lateinit var lastAdded : TextView
    private lateinit var newWhat : TextView
    private lateinit var newWhere : TextView

    private var lastRide : Ride = Ride("", "", "", Ride.getFormattedDate(), Ride.getFormattedDate())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_ride)

        this.endRide = this.findViewById(R.id.end_button)
        this.lastAdded = this.findViewById(R.id.last_added_field)
        this.newWhat = this.findViewById(R.id.what_text)
        this.newWhere = this.findViewById(R.id.where_text)

        this.endRide.setOnClickListener {
            if (!isEmpty(this.newWhat) && !isEmpty(this.newWhere)) {
                this.lastRide.bikeName = this.newWhat.text.toString().trim()
                this.lastRide.endRide = this.newWhere.text.toString().trim()

                RidesDb.endRide(this.newWhat.text.toString().trim(), this.newWhere.text.toString().trim())

                this.newWhat.text = ""
                this.newWhere.text = ""
                this.updateUI()
            }
        }

        this.updateUI()
    }

    private fun updateUI() {
        this.lastAdded.text = this.lastRide.toString()
    }

    private fun isEmpty(text: TextView): Boolean {
        return text.text.toString().isEmpty()
    }
}
