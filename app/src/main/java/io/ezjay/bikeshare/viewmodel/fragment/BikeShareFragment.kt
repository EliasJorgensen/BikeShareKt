package io.ezjay.bikeshare.viewmodel.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.RideDao
import io.ezjay.bikeshare.viewmodel.activity.*

class BikeShareFragment : Fragment() {
    // UI
    private lateinit var registerBike : Button
    private lateinit var checkBikes : Button
    private lateinit var addRide : Button
    private lateinit var endRide : Button
    private lateinit var listRides : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bike_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.registerBike = view.findViewById(R.id.register_button)
        this.checkBikes = view.findViewById(R.id.check_button)
        this.addRide = view.findViewById(R.id.add_button)
        this.endRide = view.findViewById(R.id.end_button)
        this.listRides = view.findViewById(R.id.list_button)

        this.registerEventListeners()
    }

    override fun onStart() {
        super.onStart()
        if (this.view?.context != null) {
            val hasActiveRide = RideDao.getActiveRide() != null
            if (hasActiveRide) {
                this.endRide.isEnabled = true
                this.addRide.isEnabled = false
            }
            else {
                this.endRide.isEnabled = false
                this.addRide.isEnabled = true
            }
        }
    }

    private fun registerEventListeners() {
        this.registerBike.setOnClickListener {
            this.startActivity(Intent(this.activity, RegisterBikeActivity::class.java))
        }

        this.checkBikes.setOnClickListener {
            this.startActivity(Intent(this.activity, CheckBikesActivity::class.java))
        }

        this.addRide.setOnClickListener {
            val intent = Intent(this.activity,  StartRideActivity::class.java)
            this.startActivity(intent)
        }

        this.endRide.setOnClickListener {
            val intent = Intent(this.activity, EndRideActivity::class.java)
            this.startActivity(intent)
        }

        this.listRides.setOnClickListener {
            val intent = Intent(this.activity, RideHistoryActivity::class.java)
            this.startActivity(intent)
        }
    }
}
