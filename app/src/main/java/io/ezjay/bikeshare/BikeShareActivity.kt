package io.ezjay.bikeshare

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class BikeShareActivity : AppCompatActivity() {

    // UI
    private lateinit var addRide : Button
    private lateinit var lastAdded : TextView
    private lateinit var newWhat : TextView
    private lateinit var newWhere : TextView

    private var lastRide : Ride = Ride("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_share)

        this.addRide = this.findViewById(R.id.add_button)
        this.lastAdded = this.findViewById(R.id.last_added_field)
        this.newWhat = this.findViewById(R.id.what_text)
        this.newWhere = this.findViewById(R.id.where_text)

        this.addRide.setOnClickListener {
            if (!isEmpty(this.newWhat) && !isEmpty(this.newWhere)) {
                this.lastRide.bikeName = this.newWhat.text.toString().trim()
                this.lastRide.startRide =this.newWhere.text.toString().trim()

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
