package io.ezjay.bikeshare.viewmodel.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.Bike

class BikeDropdownHolder (
    inflater : LayoutInflater,
    parent : ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.dropdown_item_bike, parent, false)) {

    private val name : TextView = itemView.findViewById(R.id.bike_name)
    var id : Long? = null

    fun bind(bike: Bike) {
        name.text = bike.name
        id = bike.id
    }
}