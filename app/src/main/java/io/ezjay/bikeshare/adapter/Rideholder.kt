package io.ezjay.bikeshare.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.entity.Ride

class RideHolder (
    inflater : LayoutInflater,
    parent : ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_ride, parent, false)) {

    private val bikeView = this.itemView.findViewById<TextView>(R.id.what_bike_ride)
    private val startView = this.itemView.findViewById<TextView>(R.id.start_ride)
    private val endView = this.itemView.findViewById<TextView>(R.id.end_ride)
    private val startTimeView = this.itemView.findViewById<TextView>(R.id.start_time)
    private val endTimeView = this.itemView.findViewById<TextView>(R.id.end_time)

    fun bind(ride: Ride) {
        this.bikeView.append(ride.bikeName)
        this.startView.append(ride.startRide)
        this.endView.append(ride.endRide)
        this.startTimeView.append(Ride.formatDate(ride.startTime))
        this.endTimeView.append(Ride.formatDate(ride.endTime))
    }
}