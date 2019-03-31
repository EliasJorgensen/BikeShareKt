package io.ezjay.bikeshare.viewmodel.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import io.ezjay.bikeshare.model.BikeshareDao
import io.ezjay.bikeshare.model.Ride

class RideArrayAdapter(
    private val values : List<Ride>
) : RecyclerView.Adapter<RideHolder>() {

    override fun onCreateViewHolder(p: ViewGroup, v: Int): RideHolder {
        val inflater = LayoutInflater.from(p.context)
        return RideHolder(inflater, p)
    }

    override fun getItemCount(): Int {
        return this.values.size
    }

    override fun onBindViewHolder(holder: RideHolder, i: Int) {
        holder.bind(this.values[i])
    }
}