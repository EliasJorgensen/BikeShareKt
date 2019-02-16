package io.ezjay.bikeshare.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.data.Ride

class RideArrayAdapter(
    context: Context,
    private val values : List<Ride>
) : ArrayAdapter<Ride>(context, R.layout.list_item_ride, values) {

    @SuppressLint("ViewHolder") // This is what the slides told me to do
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.list_item_ride, parent, false)

        val bikeView = rowView.findViewById(R.id.what_bike_ride) as TextView
        val startView = rowView.findViewById(R.id.start_ride) as TextView
        val endView = rowView.findViewById(R.id.end_ride) as TextView

        bikeView.append(this.values[position].bikeName)
        startView.append(this.values[position].startRide)
        endView.append(this.values[position].endRide)

        return rowView
    }
}