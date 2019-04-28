package io.ezjay.bikeshare.viewmodel.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.Ride
import io.ezjay.bikeshare.util.DateUtils
import io.ezjay.bikeshare.util.PictureUtils

class RideHolder (
    inflater : LayoutInflater,
    parent : ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_ride, parent, false)) {

    private var bikePhoto = itemView.findViewById<ImageView>(R.id.bike_photo)
    private var startTime = itemView.findViewById<TextView>(R.id.start_time)
    private var startLocation = itemView.findViewById<TextView>(R.id.start_location)
    private var totalCost = itemView.findViewById<TextView>(R.id.total_cost)
    private var rideDuration = itemView.findViewById<TextView>(R.id.ride_duration)
    private var bikeType = itemView.findViewById<TextView>(R.id.bike_type)

    fun bind(ride: Ride) {
        bikePhoto.setImageBitmap(PictureUtils.byteArrayToBitmap(ride.bike?.picture!!))
        startTime.text = DateUtils.format(ride.startTime!!)
        startLocation.text = ride.startLocation!!
        totalCost.text = "Price: ${ride.totalCost!!} Dkk"
        rideDuration.text = "Duration: " + DateUtils.toHoursAndMinutes(
            DateUtils.getDifference(ride.startTime!!, ride.endTime!!)
        )
        bikeType.text = "Type: ${ride.bike?.type}"
    }
}