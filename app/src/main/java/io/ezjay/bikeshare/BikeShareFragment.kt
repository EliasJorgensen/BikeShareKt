package io.ezjay.bikeshare

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import io.ezjay.bikeshare.adapter.RideArrayAdapter
import io.ezjay.bikeshare.data.RidesDb

class BikeShareFragment : Fragment() {
    // UI
    private lateinit var addRide : Button
    private lateinit var endRide : Button
    private lateinit var listRides : Button
    private lateinit var list : ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bike_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.addRide = view.findViewById(R.id.add_button)
        this.endRide = view.findViewById(R.id.end_button)
        this.listRides = view.findViewById(R.id.list_button)

        this.addRide.setOnClickListener {
            val intent = Intent(view.context,  StartRideActivity::class.java)
            this.startActivity(intent)
        }

        this.endRide.setOnClickListener {
            val intent = Intent(view.context, EndRideActivity::class.java)
            this.startActivity(intent)
        }

        this.listRides.setOnClickListener {
            if (this.list.visibility == View.GONE)
                this.list.visibility = View.VISIBLE
            else
                this.list.visibility = View.GONE
        }

        this.list = view.findViewById(R.id.ride_list)
        this.list.adapter = RideArrayAdapter(view.context, RidesDb.getRides())
    }
}
