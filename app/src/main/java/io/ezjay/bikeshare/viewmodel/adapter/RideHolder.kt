package io.ezjay.bikeshare.viewmodel.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.RideDao
import io.ezjay.bikeshare.model.Ride

class RideHolder (
    inflater : LayoutInflater,
    parent : ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_ride, parent, false)) {

    private var rideId : Long? = null

    private val bikeView = this.itemView.findViewById<TextView>(R.id.what_bike_ride)
    private val startView = this.itemView.findViewById<TextView>(R.id.start_ride)
    private val endView = this.itemView.findViewById<TextView>(R.id.end_ride)
    private val startTimeView = this.itemView.findViewById<TextView>(R.id.start_time)
    private val endTimeView = this.itemView.findViewById<TextView>(R.id.end_time)

    fun bind(ride: Ride) {
        this.bikeView.append(ride.bike?.name)
        this.startView.append(ride.startLocation)
        this.endView.append(ride.endLocation)
        this.startTimeView.append(ride.getFormattedStartTime())
        this.endTimeView.append(ride.getFormattedEndTime())

        this.rideId = ride.id

        this.itemView.setOnClickListener {
            RideDao.deleteRide(ride)
        }
    }
}