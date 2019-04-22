package io.ezjay.bikeshare.viewmodel.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.Bike
import io.ezjay.bikeshare.util.PictureUtils

class BikeHolder (
    inflater : LayoutInflater,
    parent : ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_bike, parent, false)) {

    var name: TextView = itemView.findViewById(R.id.bike_name)
    var type : TextView = itemView.findViewById(R.id.bike_type)
    var location: TextView = itemView.findViewById(R.id.bike_location)
    var price: TextView = itemView.findViewById(R.id.bike_price)
    var available: TextView = itemView.findViewById(R.id.bike_available)
    var photo: ImageView = itemView.findViewById(R.id.bike_photo)

    fun bind(bike: Bike) {
        name.text = bike.name
        type.text = "Type: ${bike.type}"
        location.text = bike.location
        price.text = "${bike.hourlyPrice.toString()} Dkk/hour"
        if (bike.available) {
            available.text = "AVAILABLE"
            available.setTextColor(Color.GREEN)
        } else {
            available.text = "IN USE"
            available.setTextColor(Color.RED)
        }
        photo.setImageBitmap(PictureUtils.byteArrayToBitmap(bike.picture!!))
    }
}